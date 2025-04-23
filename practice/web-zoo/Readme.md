Добавил subdomains - ecnlosure, feeding, healing в domain Animals

Все пункты из требуемого функционала реализованны - самый простой способ это проверить - 
swagger (http://localhost:8080/swagger-ui/index.html#/)  или же тесты

Domain - zoo.web.core.domain_services + zoo.web.core.entities
Application -  zoo.web.core.application_services
Infrastructure, Presentation - немного странное разбиение
Более понятно было бы Interface adapters - где контроллеры, dto, mappers, доступ к данным (repositories) - видимо Presentation
zoo.web.adapters

и Frameworks - где сейчас swagger + тесты - видимо Infrastructure
zoo.web.config.swagger + tests

AnimalMovedEvent + FeedingTimeEvent - положил в entities + domain_services

AnimalTransferService, FeedingOrganizationService, ZooStatisticsService - zoo.web.core.application_services

Из DDD активно использовал сущности, объекты-значения + агрегаты 
(если правильно говорю, что zoo.web.core.domain_services.AnimalFeedService,
zoo.web.core.domain_services.AnimalMoveService ими являются)

Так же из DDD не забыл про фабрики - любой сложный объект создаётся только в них + события тоже поддержал с
возможностью подписки на них


Из Clean architecture:
1) вроде бы грамотно разбил на слои
2) не имею связи вверх
3) все или почти все связи между слоями происходят через временные объекты/интерфейсы
4) относительно луковой архитектуры поддержал инверсию зависимостей для хранилищ данных
5) Вся бизнес логика осталасть в Core
