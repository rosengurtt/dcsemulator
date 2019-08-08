create procedure GetFusionRequestDetails
	@FusionRequestId nvarchar(100),
	@TableName nvarchar(100),
	@DatabaseName nvarchar(50)
as
begin
	declare @sql nvarchar(max)

	set @sql =	'select 
					FusionRequestId,
					FusionRequestXml,
					FusionResponseXml,
					RequestType ' +
				'from ' + @DatabaseName + '.dbo.' + @TableName + ' ' +
				'where FusionRequestId = ''' + @FusionRequestId + ''''

	exec sp_executesql @sql
end
