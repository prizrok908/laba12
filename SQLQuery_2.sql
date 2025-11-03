-- 1. Создание новой базы данных laba7
USE master;
GO
IF DB_ID('laba7') IS NOT NULL
    DROP DATABASE laba7;
GO
CREATE DATABASE laba7;
GO
USE laba7;
GO

-- 2. Создание таблиц
CREATE TABLE Authors (
    AuthorID INT PRIMARY KEY IDENTITY(1,1),
    FirstName NVARCHAR(50) NOT NULL,
    LastName NVARCHAR(50) NOT NULL
);

CREATE TABLE Books (
    BookID INT PRIMARY KEY IDENTITY(1,1),
    Title NVARCHAR(200) NOT NULL,
    AuthorID INT NOT NULL,
    Genre NVARCHAR(50),
    PublishYear INT CHECK (PublishYear BETWEEN 1800 AND YEAR(GETDATE())),
    CopiesAvailable INT NOT NULL DEFAULT 1 CHECK (CopiesAvailable >= 0),
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID)
);

CREATE TABLE Readers (
    ReaderID INT PRIMARY KEY IDENTITY(1,1),
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) UNIQUE,
    RegistrationDate DATE DEFAULT GETDATE()
);

CREATE TABLE Loans (
    LoanID INT PRIMARY KEY IDENTITY(1,1),
    ReaderID INT NOT NULL,
    BookID INT NOT NULL,
    LoanDate DATE DEFAULT GETDATE(),
    ReturnDate DATE NULL,
    FOREIGN KEY (ReaderID) REFERENCES Readers(ReaderID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);
GO

-- ===========================================================
-- 3. ХРАНИМЫЕ ПРОЦЕДУРЫ
-- ===========================================================

-- 🔹 Процедура 1: Фильтрация книг (по названию, жанру, году, автору — все необязательные)
CREATE OR ALTER PROCEDURE sp_SearchBooks
    @Title NVARCHAR(200) = NULL,
    @Genre NVARCHAR(50) = NULL,
    @MinYear INT = NULL,
    @MaxYear INT = NULL,
    @AuthorLastName NVARCHAR(50) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    SELECT 
        b.BookID,
        b.Title,
        a.FirstName + ' ' + a.LastName AS Author,
        b.Genre,
        b.PublishYear,
        b.CopiesAvailable
    FROM Books b
    INNER JOIN Authors a ON b.AuthorID = a.AuthorID
    WHERE
        (@Title IS NULL OR b.Title LIKE '%' + @Title + '%')
        AND (@Genre IS NULL OR b.Genre = @Genre)
        AND (@MinYear IS NULL OR b.PublishYear >= @MinYear)
        AND (@MaxYear IS NULL OR b.PublishYear <= @MaxYear)
        AND (@AuthorLastName IS NULL OR a.LastName LIKE '%' + @AuthorLastName + '%');
END;
GO

-- 🔹 Процедура 2: Фильтрация читателей и их активных займов
CREATE OR ALTER PROCEDURE sp_GetActiveLoans
    @ReaderName NVARCHAR(100) = NULL,
    @BookTitle NVARCHAR(200) = NULL,
    @OnlyUnreturned BIT = 1  -- по умолчанию только неповернутые
AS
BEGIN
    SET NOCOUNT ON;
    SELECT 
        r.FullName AS Reader,
        b.Title AS Book,
        l.LoanDate,
        l.ReturnDate
    FROM Loans l
    INNER JOIN Readers r ON l.ReaderID = r.ReaderID
    INNER JOIN Books b ON l.BookID = b.BookID
    WHERE
        (@ReaderName IS NULL OR r.FullName LIKE '%' + @ReaderName + '%')
        AND (@BookTitle IS NULL OR b.Title LIKE '%' + @BookTitle + '%')
        AND (@OnlyUnreturned = 0 OR l.ReturnDate IS NULL);
END;
GO

-- 🔹 Процедура 3: Подсчёт книг по жанру с OUTPUT-параметром
CREATE OR ALTER PROCEDURE sp_CountBooksByGenre
    @Genre NVARCHAR(50) = NULL,
    @BookCount INT OUTPUT,
    @TotalCopies INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;
    
    SELECT 
        @BookCount = COUNT(*),
        @TotalCopies = ISNULL(SUM(CopiesAvailable), 0)
    FROM Books
    WHERE @Genre IS NULL OR Genre = @Genre;

    -- Выводим список для наглядности
    SELECT Title, Genre, CopiesAvailable
    FROM Books
    WHERE @Genre IS NULL OR Genre = @Genre;
END;
GO

-- 🔹 Процедура 4: Вставка нового читателя и выдача книги (в одной транзакции)
CREATE OR ALTER PROCEDURE sp_RegisterReaderAndLoanBook
    @ReaderFullName NVARCHAR(100),
    @ReaderEmail NVARCHAR(100) = NULL,
    @BookTitle NVARCHAR(200),
    @NewLoanID INT OUTPUT
AS
BEGIN
    SET NOCOUNT ON;
    BEGIN TRY
        BEGIN TRANSACTION;

        -- Проверяем наличие книги с доступными копиями
        DECLARE @BookID INT, @Copies INT;
        SELECT @BookID = BookID, @Copies = CopiesAvailable
        FROM Books
        WHERE Title = @BookTitle;

        IF @BookID IS NULL
        BEGIN
            RAISERROR('Книга с таким названием не найдена.', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        IF @Copies <= 0
        BEGIN
            RAISERROR('Нет доступных копий этой книги.', 16, 1);
            ROLLBACK TRANSACTION;
            RETURN;
        END

        -- Добавляем читателя
        INSERT INTO Readers (FullName, Email)
        VALUES (@ReaderFullName, @ReaderEmail);
        DECLARE @ReaderID INT = SCOPE_IDENTITY();

        -- Создаём запись о выдаче
        INSERT INTO Loans (ReaderID, BookID, LoanDate)
        VALUES (@ReaderID, @BookID, GETDATE());
        SET @NewLoanID = SCOPE_IDENTITY();

        -- Уменьшаем количество доступных копий
        UPDATE Books SET CopiesAvailable = CopiesAvailable - 1
        WHERE BookID = @BookID;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        ROLLBACK TRANSACTION;
        THROW;
    END CATCH
END;
GO

-- 🔹 Процедура 5: Обновление даты возврата (возврат книги) + восстановление копии
CREATE OR ALTER PROCEDURE sp_ReturnBook
    @LoanID INT
AS
BEGIN
    SET NOCOUNT ON;

    IF NOT EXISTS (SELECT 1 FROM Loans WHERE LoanID = @LoanID)
    BEGIN
        RAISERROR('Запись о выдаче с таким ID не найдена.', 16, 1);
        RETURN;
    END

    IF (SELECT ReturnDate FROM Loans WHERE LoanID = @LoanID) IS NOT NULL
    BEGIN
        RAISERROR('Книга уже возвращена.', 16, 1);
        RETURN;
    END

    DECLARE @BookID INT;
    SELECT @BookID = BookID FROM Loans WHERE LoanID = @LoanID;

    -- Обновляем дату возврата
    UPDATE Loans
    SET ReturnDate = GETDATE()
    WHERE LoanID = @LoanID;

    -- Возвращаем копию в библиотеку
    UPDATE Books
    SET CopiesAvailable = CopiesAvailable + 1
    WHERE BookID = @BookID;

    PRINT 'Книга успешно возвращена.';
END;
GO

-- ===========================================================
-- 4. ТЕСТОВЫЕ ДАННЫЕ
-- ===========================================================

-- Авторы
INSERT INTO Authors (FirstName, LastName) VALUES 
('Лев', 'Толстой'),
('Фёдор', 'Достоевский'),
('Агата', 'Кристи');

-- Книги
INSERT INTO Books (Title, AuthorID, Genre, PublishYear, CopiesAvailable) VALUES
('Война и мир', 1, 'Роман', 1869, 3),
('Преступление и наказание', 2, 'Роман', 1866, 2),
('Десять негритят', 3, 'Детектив', 1939, 5);

-- Читатели
INSERT INTO Readers (FullName, Email) VALUES
('Сидоров А.В.', 'sidorov@example.com'),
('Кузнецова М.П.', 'kuzn@example.com');

-- Выдачи
INSERT INTO Loans (ReaderID, BookID, LoanDate, ReturnDate) VALUES
(1, 1, '2025-03-01', NULL),   -- не возвращена
(2, 3, '2025-02-10', '2025-03-05'); -- возвращена

-- ===========================================================
-- 5. ДЕМОНСТРАЦИЯ ВЫЗОВА ПРОЦЕДУР
-- ===========================================================

-- 1. Поиск книг
EXEC sp_SearchBooks @Genre = 'Роман', @MinYear = 1850;

-- 2. Активные займы
EXEC sp_GetActiveLoans @OnlyUnreturned = 1;

-- 3. Подсчёт книг по жанру с OUTPUT
DECLARE @Count INT, @Copies INT;
EXEC sp_CountBooksByGenre @Genre = 'Детектив', @BookCount = @Count OUTPUT, @TotalCopies = @Copies OUTPUT;
PRINT 'Жанр: Детектив | Книг: ' + CAST(@Count AS VARCHAR(10)) + ' | Копий: ' + CAST(@Copies AS VARCHAR(10));

-- 4. Регистрация нового читателя и выдача книги
DECLARE @NewLoan INT;
EXEC sp_RegisterReaderAndLoanBook 
    @ReaderFullName = 'Новиков И.И.',
    @ReaderEmail = 'novikov@example.com',
    @BookTitle = 'Десять негритят',
    @NewLoanID = @NewLoan OUTPUT;
PRINT 'Создана выдача с ID: ' + CAST(@NewLoan AS VARCHAR(10));

-- 5. Возврат книги
EXEC sp_ReturnBook @LoanID = 1;

-- Проверка состояния
SELECT * FROM Books;
SELECT * FROM Loans WHERE LoanID IN (1, @NewLoan);