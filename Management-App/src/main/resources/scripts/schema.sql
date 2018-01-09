CREATE TABLE IF NOT EXISTS ROLE (
  ID   INTEGER     NOT NULL AUTO_INCREMENT,
  ROLE VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (ID)
);
ALTER TABLE `ROLE`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_polish_ci;

CREATE TABLE IF NOT EXISTS USER (
  ID        INTEGER     NOT NULL AUTO_INCREMENT,
  USERNAME  VARCHAR(50) NOT NULL UNIQUE,
  PASSWORD  VARCHAR(65) NOT NULL,
  ENABLED   TINYINT     NOT NULL DEFAULT 1,
  ACTIVATED TINYINT     NOT NULL DEFAULT 0,
  ROLE_ID   INTEGER     NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID)
);
ALTER TABLE `USER`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_polish_ci;

CREATE UNIQUE INDEX USER_USERNAME_INDEX
  ON USER (USERNAME);


CREATE TABLE IF NOT EXISTS `USER_ROLE` (
  USER_ID INTEGER NOT NULL,
  ROLE_ID INTEGER NOT NULL,
  PRIMARY KEY (USER_ID, ROLE_ID),
  FOREIGN KEY (USER_ID) REFERENCES USER (ID),
  FOREIGN KEY (ROLE_ID) REFERENCES ROLE (ID)
);
ALTER TABLE `USER_ROLE`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_polish_ci;

CREATE TABLE IF NOT EXISTS ACTIVATION (
  ID              INTEGER      NOT NULL AUTO_INCREMENT,
  ACTIVATION_CODE VARCHAR(255) NOT NULL UNIQUE,
  USER_ID         INTEGER      NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES USER (ID)
);

CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  resource_ids            VARCHAR(255),
  client_secret           VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id          VARCHAR(255),
  token             LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id          VARCHAR(255),
  token             LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  authentication    LONG VARBINARY,
  refresh_token     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id       VARCHAR(255),
  token          LONG VARBINARY,
  authentication LONG VARBINARY
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code           VARCHAR(255),
  authentication LONG VARBINARY
);

CREATE TABLE IF NOT EXISTS oauth_approvals (
  userId         VARCHAR(255),
  clientId       VARCHAR(255),
  scope          VARCHAR(255),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ClientDetails (
  appId                  VARCHAR(255) PRIMARY KEY,
  resourceIds            VARCHAR(255),
  appSecret              VARCHAR(255),
  scope                  VARCHAR(255),
  grantTypes             VARCHAR(255),
  redirectUrl            VARCHAR(255),
  authorities            VARCHAR(255),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation  VARCHAR(4096),
  autoApproveScopes      VARCHAR(255)
);
