-- CREATE TABLE [user] (
--     id int IDENTITY PRIMARY KEY,
--     name nvarchar(20) NOT NULL,
--     age int NOT NULL,
--     address nvarchar(255) NOT NULL,
--     phone char(15) NOT NULL,
--     sex int NOT NULL
-- );

CREATE TABLE [admin] (
    id int IDENTITY PRIMARY KEY,
    name nvarchar(20) NOT NULL,
    password char(20) NOT NULL,
    email char(50) NOT NULL,
    phone char(15) NOT NULL
);

-- insert into [user] values(N'張三',23,N'台北市','0912345678',1)
-- insert into [user] values(N'李四',25,N'新北市','0912345678',1)
-- insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
-- insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
-- insert into [user] values(N'王八',17,N'台中市','0912345678',1)
--
-- insert into [user] values(N'張三',23,N'台北市','0912345678',1)
-- insert into [user] values(N'李四',25,N'新北市','0912345678',1)
-- insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
-- insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
-- insert into [user] values(N'王八',17,N'台中市','0912345678',1)
--
-- insert into [user] values(N'張三',23,N'台北市','0912345678',1)
-- insert into [user] values(N'李四',25,N'新北市','0912345678',1)
-- insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
-- insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
-- insert into [user] values(N'王八',17,N'台中市','0912345678',1)
--
-- insert into [user] values(N'張三',23,N'台北市','0912345678',1)
-- insert into [user] values(N'李四',25,N'新北市','0912345678',1)
-- insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
-- insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
-- insert into [user] values(N'王八',17,N'台中市','0912345678',1)
-- select * from [user]
--
-- delete from [user] where id > 25
--
-- update [user] set name = N''

insert into [admin] values('justin','Weijun528@','justinzhang515@gmail.com','0912345678')
insert into [admin] values('merry','Merry@','marry555@gmail.com','0947855522')
insert into [admin] values('test','test','test@gmail.com','094622214')

select * from admin

select * from [user]

select * from BOOK_LEND_RECORD


Select * From BOOK_LEND_RECORD Where BOOK_ID Not IN
                                     (Select BOOK_ID From BOOK_DATA)

select * from MEMBER_M