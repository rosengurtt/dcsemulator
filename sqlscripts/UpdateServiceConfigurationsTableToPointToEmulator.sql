declare @ServiceConfigurationId nvarchar(100), @Configuration xml, @endpoint xml, @emulatorUrl nvarchar(100), @emulatorServer nvarchar(100)

set @emulatorUrl = 'https://faky:8092'
set @emulatorServer= 'faky:8092'

declare cursi cursor for
select ServiceConfigurationId, [Configuration] from ServiceConfigurations

open cursi
fetch next from cursi into @ServiceConfigurationId, @Configuration

while (@@FETCH_STATUS = 0)
begin
	--select @endpoint = @Configuration.query('/Configuration/Client/Endpoint')

	select @endpoint = @Configuration.value('(/Configuration/Client/Endpoint/node())[1]', 'nvarchar(max)')
	if (not @endpoint is null)
	begin
		print @ServiceConfigurationId + ' -> ' + cast(@endpoint as nvarchar(1000))
		update ServiceConfigurations
		set [Configuration] = cast(replace(cast([Configuration] as nvarchar(max)), cast(@endpoint as nvarchar(1111)), @emulatorUrl) as xml)
		where ServiceConfigurationId = @ServiceConfigurationId
	end

	select @endpoint = @Configuration.value('(/Configuration/Client/Endpoint/node())[2]', 'nvarchar(max)')
	if (not @endpoint is null)
	begin
		print @ServiceConfigurationId + ' -> ' + cast(@endpoint as nvarchar(1000))
		update ServiceConfigurations
		set [Configuration] = cast(replace(cast([Configuration] as nvarchar(max)), cast(@endpoint as nvarchar(1111)), @emulatorUrl) as xml)
		where ServiceConfigurationId = @ServiceConfigurationId
	end

	select @endpoint = @Configuration.value('(/Configuration/Client/Endpoint/node())[3]', 'nvarchar(max)')
	if (not @endpoint is null)
	begin
		print @ServiceConfigurationId + ' -> ' + cast(@endpoint as nvarchar(1000))
		update ServiceConfigurations
		set [Configuration] = cast(replace(cast([Configuration] as nvarchar(max)), cast(@endpoint as nvarchar(1111)), @emulatorUrl) as xml)
		where ServiceConfigurationId = @ServiceConfigurationId
	end
		
	select @endpoint = @Configuration.value('(/Configuration/Client/Endpoint/node())[4]', 'nvarchar(max)')
	if (not @endpoint is null)
	begin
		print @ServiceConfigurationId + ' -> ' + cast(@endpoint as nvarchar(1000))
		update ServiceConfigurations
		set [Configuration] = cast(replace(cast([Configuration] as nvarchar(max)), cast(@endpoint as nvarchar(1111)), @emulatorUrl) as xml)
		where ServiceConfigurationId = @ServiceConfigurationId
	end

	select @endpoint = @Configuration.value('(/Configuration/AkamaiServiceNamespace/node())[1]', 'nvarchar(max)')
	if (not @endpoint is null)
	begin
		print @ServiceConfigurationId + ' -> ' + cast(@endpoint as nvarchar(1000))
		update ServiceConfigurations
		set [Configuration] = cast(replace(cast([Configuration] as nvarchar(max)), cast(@endpoint as nvarchar(1111)), @emulatorServer) as xml)
		where ServiceConfigurationId = @ServiceConfigurationId
	end

	select @endpoint = @Configuration.value('(/Configuration/EndPoint/node())[1]', 'nvarchar(max)')
	if (not @endpoint is null)
	begin
		print @ServiceConfigurationId + ' -> ' + cast(@endpoint as nvarchar(1000))
		update ServiceConfigurations
		set [Configuration] = cast(replace(cast([Configuration] as nvarchar(max)), cast(@endpoint as nvarchar(1111)), @emulatorUrl) as xml)
		where ServiceConfigurationId = @ServiceConfigurationId
	end
	fetch next from cursi into @ServiceConfigurationId, @Configuration
end

deallocate cursi