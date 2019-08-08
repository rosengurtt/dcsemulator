/****** Object:  Table [dbo].[DCSemulatorConfig]    Script Date: 30/07/2019 11:04:03 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[DCSemulatorConfig](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[mainTag] [nvarchar](200) NOT NULL,
	[airlineXpath] [nvarchar](500) NULL,
	[airportXpath] [nvarchar](500) NULL,
	[PNRXpath] [nvarchar](500) NULL,
	[requestTypesXpath] [nvarchar](1000) NULL,
	[requestTypesOperation] [nvarchar](100) NULL,
	[requestTypeValueToMatch] [nvarchar](100) NULL,
	[uniqueFragmentPattern] [nvarchar](500) NULL,
	[requestType] [nvarchar](100) NULL,
	[sortOrder] [int] NOT NULL,
	[addResponseStart] [nvarchar](1000) NULL,
	[addResponseEnd] [nvarchar](200) NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[DCSemulatorConfig] ADD  CONSTRAINT [DF_DCSemulatorConfig_sortOrder]  DEFAULT ((0)) FOR [sortOrder]
GO


