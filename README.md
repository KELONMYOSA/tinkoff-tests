# Автоматизированное тестирование продуктов компании Тинькофф

## :page_with_curl:    Содержание

➠ [Покрытый функционал](#globe_with_meridians-покрытый-функционал)

➠ [Технологический стек](#computer-технологический-стек)

➠ [Запуск тестов из терминала](#technologist-запуск-тестов-из-терминала)

➠ [Удаленный запуск тестов](#удаленный-запуск-тестов)

➠ [Сборка в Jenkins](#-главная-страница-сборки-Jenkins)

➠ [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-allure-report)

➠ [Интеграция с Allure TestOps](#-интеграция-с-allure-testops)

➠ [Интеграция с Jira](#-интеграция-с-jira)

➠ [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)

➠ [Пример запуска теста в Selenoid](#-пример-запуска-теста-в-selenoid)

➠ [Пример запуска теста в Browserstack](#-пример-запуска-теста-в-browserstack)

## <a name="globe_with_meridians-покрытый-функционал"></a>:globe_with_meridians: Покрытый функционал

### WEB (Сайт tinkoff.ru)

- [x] Проверка наличия логотипа в хедере
- [x] Проверка некорректного входа в личный кабинет
- [x] Проверка формы оформления кредитной карты
- [x] Проверка того, что стоимость выбранных подписок совпадает с итоговой
- [x] Проверка поиска авиабилетов

### MOBILE (Android приложение Тинькофф Журнал)

- [x] Проверка приветственного экрана
- [x] Проверка версии приложения
- [x] Проверка поиска
- [x] Проверка отображения курсов в учебнике
- [x] Проворка наличия статей в журнале

### API (Группа Тинькофф VK)

- [x] Проверка подписки на группу
- [x] Проверка того, что существует последняя запись на стене
- [x] Проверка лайка последней записи
- [x] Проверка комментария последней записи

## :computer: Технологический стек

<p align="center">
<img width="5%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="5%" title="Java" src="images/logo/Java.svg">
<img width="5%" title="Gradle" src="images/logo/Gradle.svg">
<img width="5%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="5%" title="Selenide" src="images/logo/Selenide.svg">
<img width="5%" title="Selenide" src="images/logo/Rest-Assured.svg">
<img width="5%" title="Selenide" src="images/logo/Appium.svg">
<img width="5%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="5%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="5%" title="Allure TestOps" src="images/logo/Allure_TO.svg">
<img width="5%" title="Allure TestOps" src="images/logo/Jira.svg">
<img width="5%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="5%" title="GitHub" src="images/logo/GitHub.svg">
<img width="5%" title="Telegram" src="images/logo/Telegram.svg">
</p>

В данном проекте автотесты написаны на <code>Java</code> с использованием <code>Selenide</code> для UI-тестов, <code>Rest-Assured</code> для REST API тестов и <code>Appium</code> для мобильного тестирования.

> В качестве библиотеки для модульного тестирования используется <code>JUnit 5</code>.
> 
> Для автоматизированной сборки проекта используется <code>Gradle</code>.
>
> <code>Selenoid</code> выполняет запуск браузеров в контейнерах <code>Docker</code>.
>
> <code>Allure Report</code> формирует отчет о запуске тестов.
>
> <code>Jenkins</code> выполняет запуск тестов.
> 
> После завершения прогона отправляются уведомления с помощью бота в <code>Telegram</code>.

## :technologist: Запуск тестов из терминала

### Локальный запуск тестов

```
gradle clean ${TAG}
```

### Удаленный запуск тестов

```
clean
${TAG}
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbrowserMobileView=${BROWSER_MOBILE}
-DremoteDriverUrl=https://${REMOTE_DRIVER_URL}
-DvideoStorage=https://${REMOTE_DRIVER_URL}/video/

-DdeviceHost=${DEVICE_HOST}
-DbrowserstackUser=${BROWSERSTACK_USER}
-DbrowserstackKey=${BROWSERSTACK_KEY}
-DbrowserstackAppUrl=${BROWSERSTACK_APP_URL}

-DvkToken=${VK_TOKEN}
-DvkID=${VK_ID}

-Dthreads=${THREADS}
```

### Параметры сборки

> <code>TAG</code> – запускаемые тесты (_web, mobile или api_).
>
> <code>REMOTE_DRIVER_URL</code> – адрес удаленного сервера, на котором будут запускаться тесты.
>
> <code>BROWSER</code> – браузер, в котором будут выполняться тесты (_по умолчанию - <code>chrome</code>_).
>
> <code>BROWSER_VERSION</code> – версия браузера, в которой будут выполняться тесты (_по умолчанию - <code>91.0</code>_).
>
> <code>BROWSER_SIZE</code> – размер окна браузера, в котором будут выполняться тесты (_по умолчанию - <code>1920x1080</code>_).
>
> <code>BROWSER_MOBILE</code> – название мобильного устройства (_например - <code>IPhone X</code>_).
>
> <code>DEVICE_HOST</code> – запуск тестов на локальном устройстве или с помощью Browserstack  (_local или browserstack_).
>
> <code>BROWSERSTACK_USER</code> – ID пользователя.
> 
> <code>BROWSERSTACK_KEY</code> – ключ пользователя.
> 
> <code>BROWSERSTACK_APP_URL</code> – URL приложения в Browserstack.
> 
> <code>VK_TOKEN</code> – access token пользователя VK или сообщества (_полные права доступа можно получить через Implicit Flow (привязан к IP)_).
> 
> <code>VK_ID</code> – ID пользователя VK.
>
> <code>THREADS</code> – количество потоков.

## <img width="4%" title="Jenkins" src="images/logo/Jenkins.svg"> Главная страница сборки [Jenkins](https://jenkins.autotests.cloud/job/KELONMYOSA_tinkoff-tests-web/)

<p align="center">
  <img src="images/screens/Jenkins.png">
</p>

## <img width="4%" title="Allure Report" src="images/logo/Allure_Report.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/nexign-page-tests/29/allure/)

### :pushpin: Главная страница Allure-отчета

<code>WEB</code>

<p align="center">
<img title="Allure Overview" src="images/screens/allure_overview_1.png">
</p>

<code>MOBILE</code>

<p align="center">
<img title="Allure Overview" src="images/screens/allure_overview_2.png">
</p>

<code>API</code>

<p align="center">
<img title="Allure Overview" src="images/screens/allure_overview_2.png">
</p>

### :pushpin: Страница с тестами

<p align="center">
<img title="Allure Behaviors" src="images/screens/allure_behaviors.png">
</p>

### :pushpin: Основной дашборд

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_overview_dashboard.png">
</p>

## <img width="4%" title="Allure TestOPS" src="images/logo/Allure_TO.svg"> Интеграция с [Allure TestOps](https://allure.autotests.cloud/launch/13526)

<p align="center">
  <img src="images/screens/Alure_TO.png" alt="dashboards">
</p>

## <img width="4%" title="Jira" src="images/logo/Jira.svg"> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-398)

<p align="center">
  <img src="images/screens/Jira.png" alt="issues">
</p>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

> После завершения сборки бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом.
<p align="center">
<img title="Telegram Notifications" src="images/screens/telegram_notifications.png">
</p>

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> Пример запуска теста в Selenoid

> К каждому тесту в отчете прилагается видео. Одно из таких видео представлено ниже.
<p align="center">
  <img title="Selenoid Video" src="images/gif/selenoid_video.gif">
</p>

## <img width="4%" title="Browserstack" src="images/logo/Browserstack.svg"> Пример запуска теста в Browserstack

<p align="center">
  <img title="Browserstack Video" src="images/gif/browserstack_video.gif">
</p>