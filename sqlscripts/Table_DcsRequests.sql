/****** Object:  Table [dbo].[DcsRequests]    Script Date: 30/07/2019 11:20:46 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[DcsRequests](
	[DcsRequestId] [uniqueidentifier] NOT NULL,
	[FusionRequestId] [uniqueidentifier] NOT NULL,
	[ConfigurationId] [uniqueidentifier] NULL,
	[RequestSourceId] [uniqueidentifier] NULL,
	[SessionId] [uniqueidentifier] NULL,
	[PNR] [varchar](10) NULL,
	[Airline] [varchar](2) NULL,
	[Airport] [varchar](3) NULL,
	[RequestType] [varchar](200) NOT NULL,
	[DcsName] [varchar](200) NOT NULL,
	[DcsVersion] [varchar](200) NOT NULL,
	[DcsRequestXml] [xml] NOT NULL,
	[DcsResponseXml] [xml] NULL,
	[RequestTime] [datetime2](7) NOT NULL,
	[ResponseTime] [datetime2](7) NULL,
	[RequestStatus] [int] NOT NULL,
	[Runs] [int] NOT NULL,
 CONSTRAINT [PK__DcsReque__4B33114C3E52440B] PRIMARY KEY CLUSTERED 
(
	[DcsRequestId] ASC
)WITH (PAD_INDEX = ON, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 80) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[DcsRequests] ADD  CONSTRAINT [DF__DcsReques__Reque__6A30C649]  DEFAULT ((0)) FOR [RequestStatus]
GO

ALTER TABLE [dbo].[DcsRequests] ADD  CONSTRAINT [DF_DcsRequests_Runs]  DEFAULT ((0)) FOR [Runs]
GO

ALTER TABLE [dbo].[DcsRequests]  WITH NOCHECK ADD  CONSTRAINT [FK__DcsReques__Incoming__403A8C7D] FOREIGN KEY([FusionRequestId])
REFERENCES [dbo].[FusionRequests] ([FusionRequestId])
GO

ALTER TABLE [dbo].[DcsRequests] CHECK CONSTRAINT [FK__DcsReques__Incoming__403A8C7D]
GO


