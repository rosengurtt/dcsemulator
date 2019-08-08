alter PROCEDURE MaskFieldsForFusionRequest
	@FusionRequestId uniqueidentifier
as
begin
	declare
		@DcsRequestId uniqueidentifier,
		@OldValue nvarchar(200), 
		@NewValue nvarchar(200), 
		@FieldTypeId int

	declare
		@translation table
			(
				FieldTypeId int,
				oldValue nvarchar(50),
				newValue nvarchar(50)
			)

	-- Populate the @translation table with the values to replace
	insert into @translation(FieldTypeId, oldValue, newValue)
	select FieldTypeId, oldValue, newValue 
	from SensitiveDataTranslation
	where FusionRequestId = @FusionRequestId

	declare curser_Replacements cursor for
	select OldValue, NewValue, FieldTypeId from @translation

	open curser_Replacements
	fetch next from curser_Replacements into @OldValue, @NewValue, @FieldTypeId

	while (@@FETCH_STATUS = 0)
	begin	
	print 'changing ' + @oldValue + ' by ' + @newValue
	print 'sanitizing DCS'
		update DcsRequestsSanitized
		set 
			DcsRequestXml = replace(cast(DcsRequestXml as nvarchar(max)), @OldValue, @NewValue),
			DcsResponseXml = replace(cast(DcsResponseXml as nvarchar(max)), @OldValue, @NewValue)
		where FusionRequestId = @FusionRequestId
		
	print 'sanitizing Fusion'
		update FusionRequestsSanitized
		set 
			FusionRequestXml = replace(cast(FusionRequestXml as nvarchar(max)), @OldValue, @NewValue),
			FusionResponseXml = replace(cast(FusionResponseXml as nvarchar(max)), @OldValue, @NewValue)
		where FusionRequestId = @FusionRequestId

		fetch next from curser_Replacements into @OldValue, @NewValue, @FieldTypeId
	end
	deallocate curser_Replacements
end
