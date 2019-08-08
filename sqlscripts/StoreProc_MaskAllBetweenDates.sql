create procedure MaskAllBetweenDates
	@startDateTime DateTime = '1900-01-01 00:00:00',
	@endDateTime DateTime = '2100-01-01 00:00:00'
as
begin
	declare @FusionRequestId uniqueidentifier

	declare curserFusion cursor for
	select FusionRequestId 
	from FusionRequestsSanitized
	where RequestTime >= @startDateTime and RequestTime <= @endDateTime
	order by RequestTime

	open curserFusion
	fetch next from curserFusion into @FusionRequestId

	while (@@FETCH_STATUS = 0)
	begin
	print @FusionRequestId
		exec FindFieldsToMaskForFusionRequest @FusionRequestId

		exec MaskFieldsForFusionRequest @FusionRequestId
	
		fetch next from curserFusion into @FusionRequestId
	end

	deallocate curserFusion
end