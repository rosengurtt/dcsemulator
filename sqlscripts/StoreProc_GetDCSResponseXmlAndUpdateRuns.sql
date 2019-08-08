CREATE PROCEDURE [dbo].[GetDCSResponseXmlAndUpdateRuns]
	@Airline NVARCHAR(200) = '%',
	@Airport NVARCHAR(200) = '%',
	@PNR NVARCHAR(10) = '%',
	@UniqueFragment NVARCHAR(1000) = '%',
	@RequestType NVARCHAR(200)
AS
BEGIN

	SET NOCOUNT ON
	DECLARE @DcsRequestId UNIQUEIDENTIFIER
	SELECT TOP 1 @DcsRequestId = DcsRequestId
	FROM DcsRequests
	WHERE 
			Airline LIKE @Airline AND
			Airport LIKE @Airport AND
			PNR LIKE @PNR AND
			RequestType LIKE @RequestType AND
			REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
			REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
			REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
			REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
			REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(
			REPLACE(REPLACE(REPLACE(
			CAST(DcsRequestXml AS varchar(MAX))
			,CHAR(9), ''), CHAR(10), ''), CHAR(11), ''), CHAR(12), ''), CHAR(13), ''), CHAR(32), ''), CHAR(33), '')
			,CHAR(34), ''), CHAR(35), ''), CHAR(36), ''), CHAR(37), ''), CHAR(38), ''), CHAR(39), ''), CHAR(40), '')
			,CHAR(41), ''), CHAR(42), ''), CHAR(43), ''), CHAR(44), ''), CHAR(45), ''), CHAR(46), ''), CHAR(47), '')
			,CHAR(58), ''), CHAR(59), ''), CHAR(60), ''), CHAR(61), ''), CHAR(62), ''), CHAR(63), ''), CHAR(64), '')
			,CHAR(91), ''), CHAR(92), ''), CHAR(93), ''), CHAR(94), ''), CHAR(95), ''), CHAR(96), ''), CHAR(123), '')
			,CHAR(124), ''), CHAR(125), ''), CHAR(126), '')
			LIKE @UniqueFragment
	ORDER BY Runs, RequestTime

	UPDATE DcsRequests
	SET Runs = Runs + 1
	WHERE DcsRequestId = @DcsRequestId

	SELECT DcsResponseXml, DCSname, DCSversion, Airline
	FROM DcsRequests
	WHERE DcsRequestId = @DcsRequestId

END