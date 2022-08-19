FROM public.ecr.aws/amazoncorretto/amazoncorretto:11
ENV APP_NAME "myapp"

RUN yum install shadow-utils.x86_64 -y && \
   yum install -y which

WORKDIR /opt/java/${APP_NAME}

COPY target/java-s3-demo-0.0.1-SNAPSHOT.jar app.jar

COPY startup.sh .
RUN chmod +x ./startup.sh

RUN groupadd -r -g 2001 ${APP_NAME}
RUN useradd -r -u 2001 -g ${APP_NAME} ${APP_NAME}
RUN chown ${APP_NAME}.${APP_NAME} /var/log/
RUN chown -R ${APP_NAME}.${APP_NAME} /opt/java/${APP_NAME}

USER ${APP_NAME}

CMD ["./startup.sh"]
