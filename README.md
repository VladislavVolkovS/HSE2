# HSE2

Лабораторная DevOps:

На данный момент выполнена на 10(но почему то перестал подгружаться контейнер):
[![Android CI/CD](https://github.com/VladislavVolkovS/HSE2/actions/workflows/hse2.yml/badge.svg)](https://github.com/VladislavVolkovS/HSE2/actions/workflows/hse2.yml)
[![DockerHub && Deploy on self-hosted](https://github.com/VladislavVolkovS/HSE2/actions/workflows/Docker.yml/badge.svg)](https://github.com/VladislavVolkovS/HSE2/actions/workflows/Docker.yml)


На 4-5:
- Выбрано приложение с курсовой работы (Android)
- С помощью gradle подгружены все зависимости
- Создан простой пайплайн

На 6-7:
- Добавлена выгрузка apk файла после успешной сборки(debug, release)
- Добавлена выгрузка папки содержащей lint отчет в трех форматах(html, xml, txt) 

- Добавлена выгрузка установочного приложения .apk(debug, release) в телеграм канал
- Добавлена выгрузка lint отчета в формате html для возможности посмотреть в браузере в телеграм канал

- Добавлена упаковка готового приложения в Docker-контейнер.
- Добавлена выгрузка готового Docker-контейнера после успешной сборки контейнера в Docker Hub.

На 8-9:
- Добавлено развертываение на self-hosted(ошибку API выдает, хотя первоначально запускался)

На 10:
- Добавлено unit тестирование, а также выгрузка артефакта - html отчета о тестировании в телеграм канал

