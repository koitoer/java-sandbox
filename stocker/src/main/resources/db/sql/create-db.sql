CREATE TABLE stocks (
  symbol       VARCHAR(5) PRIMARY KEY,
  companyName  VARCHAR(30),
  sector       VARCHAR(50),
  openPrice    DECIMAL(5, 2),
  closePrice   DECIMAL(5, 2),
  currentPrice DECIMAL(5, 2),
  totalVolume  BIGINT,
  updateTime   TIMESTAMP
);

CREATE TABLE bank (
  accountId     BIGINT,
  recordId      BIGINT,
  bankOperation VARCHAR(7),
  amount        DECIMAL(5, 2),
  operationTime TIMESTAMP,
  PRIMARY KEY (accountId, recordId)
);

CREATE TABLE accounts (
  accountId      VARCHAR(100) PRIMARY KEY,
  stockList      VARCHAR(100),
  stockWatchList VARCHAR(100),
  customerName   VARCHAR(100)
);


CREATE TABLE stockOperation (
  accountId       BIGINT,
  operationId     BIGINT,
  stockMovement   VARCHAR(10),
  transactionCost DECIMAL(5, 2),
  stock           VARCHAR(5),
  numberStocks    BIGINT,
  PRIMARY KEY (accountId, operationId)
)
