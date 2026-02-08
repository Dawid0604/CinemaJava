#!/bin/sh

cat <<EOF > /pgadmin4/servers.json
{
  "Servers": {
    "1": {
      "Name": "${PGADMIN_SERVER_NAME}",
      "Group": "Servers",
      "Host": "${POSTGRES_HOST}",
      "Port": ${POSTGRES_PORT},
      "MaintenanceDB": "${POSTGRES_DB_NAME}",
      "Username": "${POSTGRES_USER}",
      "Password": "${POSTGRES_USER_PASSWORD}",
      "SSLMode": "prefer"
    }
  }
}
EOF

exec /entrypoint.sh

