/****** Object:  Table [dbo].[FusionRequests]    Script Date: 30/07/2019 11:22:02 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[FusionRequests](
	[FusionRequestId] [uniqueidentifier] NOT NULL,
	[RequestSourceId] [uniqueidentifier] NOT NULL,
	[SessionId] [uniqueidentifier] NULL,
	[PNR] [varchar](10) NULL,
	[Airline] [varchar](2) NULL,
	[Airport] [varchar](3) NULL,
	[RequestType] [varchar](200) NOT NULL,
	[FusionRequestXml] [xml] NOT NULL,
	[FusionResponseXml] [xml] NULL,
	[RequestTime] [datetime2](7) NOT NULL,
	[ResponseTime] [datetime2](7) NULL,
	[RequestStatus] [int] NOT NULL,
 CONSTRAINT [PK__IncomingReq__FC863351398D8EEE] PRIMARY KEY CLUSTERED 
(
	[FusionRequestId] ASC
)WITH (PAD_INDEX = ON, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[FusionRequests] ADD  CONSTRAINT [DF__IncomingRequ__Reque__3B75D760]  DEFAULT (getdate()) FOR [RequestTime]
GO

ALTER TABLE [dbo].[FusionRequests] ADD  CONSTRAINT [DF__IncomingRequ__Reque__6B24EA82]  DEFAULT ((0)) FOR [RequestStatus]
GO


