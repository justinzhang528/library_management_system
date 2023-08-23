-- CREATE TABLE [user] (
--     id int IDENTITY PRIMARY KEY,
--     name nvarchar(20) NOT NULL,
--     age int NOT NULL,
--     address nvarchar(255) NOT NULL,
--     phone char(15) NOT NULL,
--     sex int NOT NULL
-- );

insert into [user] values(N'張三',23,N'台北市','0912345678',1)
insert into [user] values(N'李四',25,N'新北市','0912345678',1)
insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
insert into [user] values(N'王八',17,N'台中市','0912345678',1)

insert into [user] values(N'張三',23,N'台北市','0912345678',1)
insert into [user] values(N'李四',25,N'新北市','0912345678',1)
insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
insert into [user] values(N'王八',17,N'台中市','0912345678',1)

insert into [user] values(N'張三',23,N'台北市','0912345678',1)
insert into [user] values(N'李四',25,N'新北市','0912345678',1)
insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
insert into [user] values(N'王八',17,N'台中市','0912345678',1)

insert into [user] values(N'張三',23,N'台北市','0912345678',1)
insert into [user] values(N'李四',25,N'新北市','0912345678',1)
insert into [user] values(N'黃五',18,N'新竹市','0912345678',1)
insert into [user] values(N'楊六',21,N'桃園市','0912345678',0)
insert into [user] values(N'王八',17,N'台中市','0912345678',1)
select * from [user]

delete from [user] where id > 25

update [user] set name = N''
