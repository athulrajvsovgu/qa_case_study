FROM openjdk:8
COPY . /usr/src/qa_case_study
WORKDIR /usr/src/qa_case_study
RUN javac *.java
CMD ["java", "Main"]
