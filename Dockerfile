FROM gradle:8.1.1-jdk17-alpine
WORKDIR /usr/src/HSE2
COPY . /usr/src/HSE2
ARG ANDROID_SDK_VERSION=9477386
ENV ANDROID_SDK_ROOT /usr/src/android-sdk
RUN mkdir -p ${ANDROID_SDK_ROOT}/cmdline-tools && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_SDK_VERSION}_latest.zip && \
    unzip *tools*linux*.zip -d ${ANDROID_SDK_ROOT}/cmdline-tools && \
    mv ${ANDROID_SDK_ROOT}/cmdline-tools/cmdline-tools ${ANDROID_SDK_ROOT}/cmdline-tools/tools && \
    rm *tools*linux*.zip
ENV PATH ${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/cmdline-tools/tools/bin:${ANDROID_SDK_ROOT}/platform-tools:${ANDROID_SDK_ROOT}/emulator
RUN yes | sdkmanager --licenses && \
	sdkmanager "platforms;android-33" "build-tools;33.0.0" "build-tools;33.0.0" "build-tools;30.0.3" && \
	cd /usr/src/HSE2
RUN gradle build
