-- 1. Создание новой базы данных laba8
USE master;
GO
IF DB_ID('laba8') IS NOT NULL
    DROP DATABASE laba8;
GO
CREATE DATABASE laba8;
GO
USE laba8;
GO

-- 2. Создание таблицы счетов
CREATE TABLE Accounts (
    AccountID INT PRIMARY KEY,
    AccountHolder NVARCHAR(100) NOT NULL,
    Balance DECIMAL(15,2) NOT NULL CHECK (Balance >= 0)
);
GO

-- Начальные данные (используем INSERT только для инициализации — далее только UPDATE/DELETE)
INSERT INTO Accounts (AccountID, AccountHolder, Balance) VALUES
(101, 'Иванов А.А.', 50000.00),
(102, 'Петров Б.Б.', 30000.00),
(103, 'Сидорова В.В.', 20000.00),
(104, 'Кузнецов Г.Г.', 10000.00);
GO

-- ===========================================================
-- 1. Режим: Автоматическая фиксация (Auto-commit) — по умолчанию
-- Каждая команда DML — отдельная транзакция
-- ===========================================================
PRINT '=== Режим 1: Автоматическая фиксация ===';
-- Пример: обновление баланса одного счёта
UPDATE Accounts SET Balance = Balance - 5000 WHERE AccountID = 101;
UPDATE Accounts SET Balance = Balance + 5000 WHERE AccountID = 102;
-- Каждая команда автоматически зафиксирована
SELECT * FROM Accounts WHERE AccountID IN (101, 102);
GO

-- ===========================================================
-- 2. Режим: Неявные транзакции (Implicit Transactions)
-- После завершения одной транзакции следующая начинается автоматически
-- ===========================================================
PRINT '=== Режим 2: Неявные транзакции ===';
SET IMPLICIT_TRANSACTIONS ON;
GO

-- Первая операция начинает транзакцию
UPDATE Accounts SET Balance = Balance - 3000 WHERE AccountID = 103;
-- Вторая — в той же транзакции
UPDATE Accounts SET Balance = Balance + 3000 WHERE AccountID = 104;

-- Проверим: изменения ещё не видны другим сессиям (но в нашей — видны)
SELECT * FROM Accounts WHERE AccountID IN (103, 104);

-- Явно фиксируем
COMMIT TRANSACTION;
SET IMPLICIT_TRANSACTIONS OFF; -- возвращаем в обычный режим
GO

-- ===========================================================
-- 3. Режим: Явные транзакции (Explicit Transactions)
-- Полный контроль через BEGIN / COMMIT / ROLLBACK
-- ===========================================================
PRINT '=== Режим 3: Явные транзакции ===';
BEGIN TRANSACTION;

-- Перевод средств: снятие и зачисление
UPDATE Accounts SET Balance = Balance - 10000 WHERE AccountID = 101;
UPDATE Accounts SET Balance = Balance + 10000 WHERE AccountID = 103;

-- Имитация ошибки: проверим, достаточно ли средств
IF (SELECT Balance FROM Accounts WHERE AccountID = 101) < 0
BEGIN
    ROLLBACK TRANSACTION;
    PRINT 'Ошибка: недостаточно средств. Транзакция отменена.';
END
ELSE
BEGIN
    COMMIT TRANSACTION;
    PRINT 'Перевод успешно выполнен.';
END
GO

-- ===========================================================
-- 4. Режим: Вложенные транзакции (Nested Transactions)
-- SQL Server поддерживает вложенность через @@TRANCOUNT
-- ===========================================================
PRINT '=== Режим 4: Вложенные транзакции ===';

BEGIN TRANSACTION OuterTran;
    UPDATE Accounts SET Balance = Balance - 2000 WHERE AccountID = 102;

    -- Вложенная транзакция
    BEGIN TRANSACTION InnerTran;
        UPDATE Accounts SET Balance = Balance + 2000 WHERE AccountID = 104;
    COMMIT TRANSACTION InnerTran; -- уменьшает @@TRANCOUNT, но не фиксирует окончательно

-- Фиксируем внешнюю транзакцию — только теперь изменения сохранятся
COMMIT TRANSACTION OuterTran;

SELECT * FROM Accounts WHERE AccountID IN (102, 104);
GO

-- ===========================================================
-- Дополнительно: демонстрация отката при ошибке (для ACID)
-- ===========================================================
PRINT '=== Демонстрация отката при ошибке (Atomicity) ===';
BEGIN TRY
    BEGIN TRANSACTION;
    UPDATE Accounts SET Balance = Balance - 60000 WHERE AccountID = 101; -- попытка снять больше, чем есть
    UPDATE Accounts SET Balance = Balance + 60000 WHERE AccountID = 103;

    -- Искусственная проверка
    IF (SELECT Balance FROM Accounts WHERE AccountID = 101) < 0
        THROW 50001, 'Недостаточно средств!', 1;

    COMMIT TRANSACTION;
END TRY
BEGIN CATCH
    ROLLBACK TRANSACTION;
    PRINT 'Транзакция отменена из-за ошибки: ' + ERROR_MESSAGE();
END CATCH;
GO

-- Финальное состояние
SELECT * FROM Accounts;
GO