--Script para crear usuario
CREATE USER SOUNDMUSIC IDENTIFIED BY SOUNDMUSIC2019
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP";
GRANT RESOURCE TO SOUNDMUSIC;
GRANT CONNECT TO SOUNDMUSIC;