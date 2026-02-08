FROM dpage/pgadmin4:9.11.0
USER root

COPY setup-server.sh /setup-server.sh
RUN chmod +x /setup-server.sh

ENTRYPOINT [ "/setup-server.sh" ]
