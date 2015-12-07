USE [master]
GO
/****** Object:  Database [contacts]    Script Date: 12/07/2015 14:54:19 ******/
CREATE DATABASE [contacts] ON  PRIMARY 
( NAME = N'contacts', FILENAME = N'E:\SQLDB\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\contacts.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'contacts_log', FILENAME = N'E:\SQLDB\MSSQL10_50.MSSQLSERVER\MSSQL\DATA\contacts_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [contacts] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [contacts].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [contacts] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [contacts] SET ANSI_NULLS OFF
GO
ALTER DATABASE [contacts] SET ANSI_PADDING OFF
GO
ALTER DATABASE [contacts] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [contacts] SET ARITHABORT OFF
GO
ALTER DATABASE [contacts] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [contacts] SET AUTO_CREATE_STATISTICS ON
GO
ALTER DATABASE [contacts] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [contacts] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [contacts] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [contacts] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [contacts] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [contacts] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [contacts] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [contacts] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [contacts] SET  DISABLE_BROKER
GO
ALTER DATABASE [contacts] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [contacts] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [contacts] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [contacts] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [contacts] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [contacts] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [contacts] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [contacts] SET  READ_WRITE
GO
ALTER DATABASE [contacts] SET RECOVERY FULL
GO
ALTER DATABASE [contacts] SET  MULTI_USER
GO
ALTER DATABASE [contacts] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [contacts] SET DB_CHAINING OFF
GO
EXEC sys.sp_db_vardecimal_storage_format N'contacts', N'ON'
GO
USE [contacts]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12/07/2015 14:54:20 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[surName] [varchar](50) NULL,
	[login] [varchar](50) NULL,
	[password] [varchar](100) NULL,
	[email] [varchar](50) NULL,
	[phoneNumber] [varchar](50) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  StoredProcedure [dbo].[checkUserEntry]    Script Date: 12/07/2015 14:54:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[checkUserEntry]	
	@login varchar(50),
	@password varchar(50),
	@passwordConfirm varchar(50),
	@email varchar(50),
	@phoneNumber varchar(50)	
AS
    Declare @res varchar(500); 
    DECLARE @NewLineChar AS CHAR(2) = CHAR(13) + CHAR(10);
    Set @res='';
    
	if((Select id from Users where Users.login=@login)>0 )
	begin
	 	 Set @res=@res+'- Такой логин уже существует.';
	end;
	
	if((Select id from Users where Users.password=@password)>0)
	begin
		 Set @res=@res+'- Такой пароль уже существует.';
	end;
	
	if(@password!=@passwordConfirm)
	begin
	 	 Set @res=@res+'- Пароль подтверждён не верно.';
	end;
	
	if((Select id from Users where email=@email)>0)
	begin
	 	 Set @res=@res+'- Такой email уже существует.';
	end;
	
	if((Select id from Users where phoneNumber=@phoneNumber)>0)
	begin
	 	 Set @res=@res+'- Номер телефона уже существует';
	end		
		
	Select @res as result;
GO
/****** Object:  StoredProcedure [dbo].[checkUser]    Script Date: 12/07/2015 14:54:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[checkUser]
	@login varchar(50),
	@password varchar(50)
AS
	if((Select id from Users where login=@login and password=@password)is not null)
	begin
	 Select * from Users where login=@login and password=@password;	 
	end
GO
/****** Object:  StoredProcedure [dbo].[addUser]    Script Date: 12/07/2015 14:54:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[addUser]
	@name varchar(50),
	@surName varchar(50),
	@login varchar(50),
	@password varchar(50),
	@email varchar(50),
	@phoneNumber varchar(50)	
AS
	Declare @id int;
	Set @id=(Select id from Users where login=@login);
    if(@id>0)
    begin
     update Users set name=@name,surName=@surName,password=@password,email=@email,phoneNumber=@phoneNumber where id=@id;
    end;
    else 
     begin
	    insert into Users(name,surName,login,password,email,phoneNumber) values(@name,@surName,@login,@password,@email,@phoneNumber);
    end
GO
