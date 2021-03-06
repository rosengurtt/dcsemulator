alter PROCEDURE [dbo].[FindFieldsToMaskForFusionRequest]
	@FusionRequestId uniqueidentifier
AS
BEGIN

declare @RequestType varchar(200), @FusionRequestXml xml, @FusionResponseXml xml, @oldValue nvarchar(100), @newValue nvarchar(100), @sql nvarchar(1000), @n int

select	
		@RequestType = RequestType,
		@FusionRequestXml = FusionRequestXml,
		@FusionResponseXml = FusionResponseXml
from	FusionRequests
where	fusionRequestId = @fusionRequestId

declare @temp table
(
	id  int NOT NULL PRIMARY KEY,
	oldValue nvarchar(50),
	newValue nvarchar(50)
)

-- Find first names
---------------------------------------------------------------------------------------------------------------------
delete from @temp

set @n = 1

-- Populate @temp table with first names to replace
while @n < 15
begin
	set @sql = 'select @oldValue = @FusionRequestXml.value(''(//*/FirstName)[' + cast(@n as varchar(10)) + ']'',''NVARCHAR(100)'')'
	exec sp_executesql @sql,  N'@FusionRequestXml xml, @oldValue nvarchar(100) OUTPUT', @FusionRequestXml, @oldValue OUTPUT

	select @newValue = [value] from RandomData where FieldType = 1 and len([value]) = len(@oldValue) order by NewId()
	insert into @temp(id, oldValue, newValue) values (@n, @oldValue, @newValue)				

	set @n = @n + 1
end

-- Remove nulls
delete from @temp where oldValue is null
-- Remove duplicates
delete from @temp 
where id in (select f1.id from @temp f1, @temp f2 where f1.oldValue = f2.oldValue and f1.id > f2.id)

insert into SensitiveDataTranslation(FusionRequestId, FieldTypeId, OldValue, NewValue)
select @fusionRequestId, 1, oldValue, newValue
from @temp SensitiveDataTranslation


-- Find Last Names
---------------------------------------------------------------------------------------------------------------------
delete from @temp

set @n = 1

-- Populate @temp table with first names to replace
while @n < 15
begin
	set @sql = 'select @oldValue = @FusionRequestXml.value(''(//*/LastName)[' + cast(@n as varchar(10)) + ']'',''NVARCHAR(100)'')'
	exec sp_executesql @sql,  N'@FusionRequestXml xml, @oldValue nvarchar(100) OUTPUT', @FusionRequestXml, @oldValue OUTPUT

	select @newValue = [value] from RandomData where FieldType = 2 and len([value]) = len(@oldValue) order by NewId()
	insert into @temp(id, oldValue, newValue) values (@n, @oldValue, @newValue)				

	set @n = @n + 1
end

-- Remove nulls
delete from @temp where oldValue is null
-- Remove duplicates
delete from @temp 
where id in (select f1.id from @temp f1, @temp f2 where f1.oldValue = f2.oldValue and f1.id > f2.id)

insert into SensitiveDataTranslation(FusionRequestId, FieldTypeId, OldValue, NewValue)
select @fusionRequestId, 2, oldValue, newValue
from @temp SensitiveDataTranslation

-- Remove nulls
delete from @temp where oldValue is null
-- Remove duplicates
delete from @temp 
where id in (select f1.id from @temp f1, @temp f2 where f1.oldValue = f2.oldValue and f1.id > f2.id)

insert into SensitiveDataTranslation(FusionRequestId, FieldTypeId, OldValue, NewValue)
select @fusionRequestId, 1, oldValue, newValue
from @temp SensitiveDataTranslation

-- Find dates of birth
---------------------------------------------------------------------------------------------------------------------
delete from @temp

set @n = 1

-- Populate @temp table with first names to replace
while @n < 15
begin
	set @sql = 'select @oldValue = @FusionRequestXml.value(''(//*/DateOfBirth)[' + cast(@n as varchar(10)) + ']'',''NVARCHAR(100)'')'
	exec sp_executesql @sql,  N'@FusionRequestXml xml, @oldValue nvarchar(100) OUTPUT', @FusionRequestXml, @oldValue OUTPUT

	select @newValue = [value] from RandomData where FieldType = 3 order by NewId()
	insert into @temp(id, oldValue, newValue) values (@n, @oldValue, @newValue)				

	set @n = @n + 1
end

-- Remove nulls
delete from @temp where oldValue is null
-- Remove duplicates
delete from @temp 
where id in (select f1.id from @temp f1, @temp f2 where f1.oldValue = f2.oldValue and f1.id > f2.id)

insert into SensitiveDataTranslation(FusionRequestId, FieldTypeId, OldValue, NewValue)
select @fusionRequestId, 3, oldValue, newValue
from @temp SensitiveDataTranslation

-- Find identity documents
---------------------------------------------------------------------------------------------------------------------
delete from @temp

set @n = 1

-- Populate @temp table with first names to replace
while @n < 15
begin
	set @sql = 'select @oldValue = @FusionRequestXml.value(''(//*/NativePassengerId)[' + cast(@n as varchar(10)) + ']'',''NVARCHAR(100)'')'
	exec sp_executesql @sql,  N'@FusionRequestXml xml, @oldValue nvarchar(100) OUTPUT', @FusionRequestXml, @oldValue OUTPUT

	select @newValue = [value] from RandomData where FieldType = 4 order by NewId()
	insert into @temp(id, oldValue, newValue) values (@n, @oldValue, @newValue)	

	set @n = @n + 1
end

-- Remove nulls
delete from @temp where oldValue is null
-- Remove duplicates
delete from @temp 
where id in (select f1.id from @temp f1, @temp f2 where f1.oldValue = f2.oldValue and f1.id > f2.id)

insert into SensitiveDataTranslation(FusionRequestId, FieldTypeId, OldValue, NewValue)
select @fusionRequestId, 4, oldValue, newValue
from @temp SensitiveDataTranslation

END
