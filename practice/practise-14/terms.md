# Типы связей классов
## Композиция
#### внутренний класс создаётся в конструкторе второго

```java
public class Parent {
  private Child child;

  public Parent(chParamsT chParams) {
    this.child = new Child(chParams);
  }
}
```

## Аггрегация
#### внутренний класс передаётся в конструктор второго

```java
public class Parent {
  private Child child;

  public Parent(Child child) {
    this.child = child;
  }
}
```

## Ассоциация
#### внутренний класс имеет getter, setter внутри второго

```java
public class Parent {
  @Getter
  @Setter
  private Child child;

  public Parent() {
  }
}
```

Разница в том, что хотим указывать время и обязательность существования внутреннего класса

# OOП

## Инкапсуляция
#### Хотим контролировать доступ к полям класса чтобы не думать при их изменении извне о внутренней логике класса
будем использовать модификаторы доступа:
* для классов:
  + public - виден всем
  + default - виден только внутри пакета

* для аттрибутов и методов:
  + public - виден всем
  + default - виден только внутри пакета
  + protected - виден только внутри пакета и подклассах
  + private - виден только внутри основного класса

```java
public class ClassPublic {
  public int x1;
  int x2;
  protected int x3;
  private int x3;
}

class ClassDefault {
}
```

## Полиморфизм
#### Хотим уметь идентично работать с объектами разных типов, имеющих идентичные сигнатуры функций, но разные реализаци
давайте использовать интерфейсы/абстрактные классы/наследование(для логически родственных) чтобы этого добиться

## Наследование
#### У нас есть класс реализующий свою функциональность, хотим практически такой же, но для других actor-ов + не хотим копипастить тк осложняет дебаг,читаемость,поддержку...
давайте создадим новый класс наследованным от изначального, который частично передаст ему свои методы/аттрибуты

## ООП
#### У нас есть много программистов разных уровней и разных возможностей, давайте договоримся о минимальных идеях при написании кода, чтобы между людьми он был схож/понятен - наследование, полиморфизм, инкапсуляцию

# Принципы разработки

## YAGNI - you aren't gonna need it
#### давайте не будем сохранять в кодовой базе неиспользуемый код + не будем писать тот, что пригодится когда-то в будущем
чтобы этот принцип был адекватным лучше иметь ввиду далёкое будущее, где мы пишем лишние аттрибуты/методы более уникальными и не уверены в их полезности в других конфигурациях

## DRY - don't repeat yourself
#### давайте не будем переписывать то, что уже написано в нашей кодовой базе + не будем использовать практически идентичный код дважды
нужно аккуратно пользоваться этой идеей, тк это может породить лишние связи между модулями - но теоретически хорошо, что не надо будет тестировать дважды/исправлять дважды/читать дважды/...

## KISS - keep it simple, stupid
#### давайте не будем писать сложный код/сложную структуру кода, если этого не требуется для решения задачи - простые системы работают быстрее и надёжнее
я бы изменил и сократил до пишите код, который легко поймут другие, пока это возможно

## BDUF - big design up front
#### давайте планировать, заранее обильно обсуждать архитектуру, возможные трудности, а потом писать


## Бритва Оккама
#### давайте не создавать сущности заранее, без необходимости

## SOLID
### S - single responsibility
#### с классом должен работать только 1 actor = у класса есть ровно 1 причина для изменения
у actor-ов будут меняться требования к классу и мы захотим что-то поправить - но если их много, то сложно и долго аккуратно учитывать и не задевать остальные контракты

### L - liskov substitution
#### ребёнок должен идентично выполнять функционал родителя
хотим гарантию, что родителя везде можно заменить на ребёнка - в реальных системах при соблюдении вытекают сложности по типу программирования по контракту (проверки на тип класса)

### I - interface segregation
#### толстые интерфейсы хотим разделить на более тонкие
не хотим допускать ситуацию когда объекты удовлетворяют интерфейсу, но часть методов возвращает ошибку (unimplemented) - но всё-ещё хотим объединять эти объекты под общий тип - давайте сделаем иерархию интерфейсов

### D - dependency inversion
#### модули верхнего уровня не должны зависить от модулей нижнего
пусть оба уровня будут зависить от интерфейса, а также этот интерфейс должен быть объявлен в родительском модуле, тк иначе родительский модуль всё-ещё будет зависеть от нижнего

### O - open/closed principle
#### для расширению функциональности класса хотим не менять сам класс, а менять интерфейсы и объекты которые он использует
S+D

State - значение внутри класса позволяет определить поведение класса
Медиатор - класс вызыввающий методы других классов
Стратегия  - класс определяющий поведение алгоритма
Шаблонный метод - абстраный метод, переопределяемый в детях
memento (хранитель) - (в бд снимок бд) (в классах обычно сохр экземпляр класса) - дублированное состояние, к которому можно вернутся


# Паттерны

## Паттерны создания


## Поведенческие паттерны


### Цепочка обязанностей (Chain of responsibility)
это поведенческий паттерн проектирования, который позволяет передавать запросы последовательно по цепочке обработчиков. Каждый последующий обработчик решает, может ли он обработать запрос сам и стоит ли передавать запрос дальше по цепи.

### Команда (Command)
Часто под идейно идентичными структурно объектами у нас различная бизнес логика

Чтобы внутри самих структур не писать сложные вызовы/ифать давайте:
1. создадим interface команды (например метод execte(...))
2. внутрь структур будем хранить этот интерфейс (скорее всего аггрегацией (передача при иниц))
3. внутри структур будем вызывать методы интерфейса

### Интерпретатор (Interpreter)
Паттерн Интерпретатор (Interpreter) определяет представление грамматики для заданного языка и интерпретатор предложений этого языка. Как правило, данный шаблон проектирования применяется для часто повторяющихся операций.

### Итератор (Iterator)
Итератор — это поведенческий паттерн проектирования, который даёт возможность последовательно обходить элементы составных объектов, не раскрывая их внутреннего представления.

### Посредник (Mediator)
Пусть мы хотим между множеством класса общаться друг к другу - но это слишком много
потенциальных связей - давайте сделаем класс посредник у которого будут методы
нотифакации

### Хранитель (Memento)
поведенческий шаблон проектирования, позволяющий не нарушая инкапсуляцию зафиксировать и сохранить внутреннее состояние объекта так, чтобы позднее восстановить его в это состояние.

### Наблюдатель (Observer)
Пусть у нас есть класс, состояния/события которого важны другим - хотим как-то уметь
поддерживать/реагировать на его изменения

Давайте:
1. Сделаем интерфейс наблюдателя с методами реакций на изменение
2. В наблюдаемом классе будем поддерживать текущий массив интересующися классов
   (наблюдателей) - тогда добавим методы Subscribe(name, obsI), Unsubsctibe(name)
3.  В наблюдаемом классе добавим методы нотификации , проходящие наблюдателей

### Состояние (State)
Пусть у нашего объекта много различных стадий жизни - хотим разбить этот код:

давайте сделаем общий интерфейс состояний (напр process, changeState) и будем просто
вызывать их методы, пока не потребуется какого-то внешнего вмешательства в работу

Контекст между состояниями можно прокидывать как аргументы, а так же в changeState при 'иницализации'

Сам метод process подразумевает стратегию внутри , а к состоянию относиться в основном changeState

### Стратегия (Strategy)
Пусть в нашем большом классе используется задача, которую можно решить разными алгоритмами

Чтобы была возможность менеджить какой алгоритм будет использоваться создадим:

1. интерфейс задачи
2. его реализации - конкретные алгоритмы - стратегии
3. объединим с изначальным классом ассоциацией (getter setter) или аггрегацией (через конструктор)

### Шаблонный метод (Template method)
Пусть в нашем большом классе есть множество задач и часть из них (не фикс кол-во задач ранее)
мы хотим менять на более специфические реализации

Давайте сделаем все теоретически изменяемые задачи абстрактными (с возможно стандартной реализацией)

шаблонный метод - метод такого класса вызывающий абстрактные методы
(который, вероятно, абстрактным делать не стоит)

### Посетитель  (Visitor)
Посетитель — это поведенческий паттерн проектирования, который позволяет добавлять в программу новые операции, не изменяя классы объектов, над которыми эти операции могут выполняться.

## Структурные паттерны

### Адаптер (Adapter)
Пусть у нас в моменте есть мощный класс, который умеет в сложную логику и есть
второй класс, которому эта логика нужна, но немного в другом виде.

Так как изначальный класс уже используется другими классами, то менять его
интерфейс проблематично - давайте создадим класс адаптер, который будет
поддерживать новый необходимый интерфейс используя старый/создав её один
более мощный интерфейс над старым объектом


### Мост (Bridge)
Мы уже привыкли работать с конкретными реализациями через интерфейсы, но, что
если мы хотим не изменить логику внутри интерфейсных классов, а хотим изменить логику
работы и функциональность класса работающего с самими интерфейсами.

Давайте с интерфейсами будет работать отдельный класс - а при желании
добавить/кардинально поменять логику будем от него наследоваться

### Компоновщик (Composite)
Пусть у нас есть много разных по типу объектов для которых мы хотим запустить
определённую функцию - но по бизнес логике не можем хранить все объекты у себя/
запуск функции зависит от связей между этими объектами (например они вложены)/...

Если сами объекты не знают как они влияют на другие объекты, то никто не знает -
так что идейно сами объекты могут направить для кого далее запускать - давайте
сделаем интерфейс методом которого будет запуск нужной функции - теперь
достаточно запускать его от внешних объектов, чтобы они делегировали запуск по
интерфейсу внутренним

### Декоратор (Decorator)
Хотим перед использованием метода делать предварительную логику, а так же после
завершения метода - обернём метод другим методом (если хотим переиспользовать то
добавим внутренний метод как аргумент)

### Фасад (Facade)
Пусть у нас есть множество сложносплетённых классов у которых надо поддерживать
некий порядок инициализации/вызовав/просто слишком много объектов и публичных методов

Давайте ужмём их всех в 1 класс, сократим часть логики - теперь клиенту намного
удобнее просто использовать наши функции и меньше вариативности для ошибок

### Легковес (Flyweight)
Пусть у нас много объектов ссылающихся на меньшее количество различных данных,
которые суммарно весят много - давайте внутри самих объектов хранить указатель
на эти данные в другом месте в памяти

А при создании объекта, в его фабрике, можно поддерживать и размещение тяжёлых
данных (например hive)

### Заместитель (Proxy)
Пусть у нас есть большой сложный привередливый по каким-то входным данным/ресурсам
класс - давайте надстроим над ним логику, поддерживающую тот же интерфейс, но
дающие другие гарантии/делающим доп действия - это и будет заместителем



### Что такое Docker? зачем он нужен?
Docker - ui + engine, поддерживающий контейнеризацию

Хотим предоставить различным сервисам изоляцию в используемых зависимостях + иметь общий интерфейс управления ими

### Как поднять бд в докере?
Достаточно создать новый сервис и описать необходимые для конкретной бд параметры

### Как подключить бд к приложению?
Глобально для этого ничего не нужно, но приятно использовать готовые библиотеки (драйвера) для подключения, и надстраивать над ними
различные паттерны или же, что в java более приоритетно использовать ORM

### Что такое Repository?
Глобально это паттерн транляции запросов в бд

Локально это интерфейс ORM-а

В java расширяют template abstract class JpaRepository, чтобы имплементировать паттерн

### Что такое Hibernate?
Hibernate - популярный ORM в java

Основная функциональность (deepseek):

- Преобразование Java-объектов в записи БД (и наоборот).
- Автоматизация SQL-запросов (избегание ручного написания INSERT, UPDATE, DELETE).
- Управление транзакциями (JPA-транзакции, @Transactional).
- Кэширование (ускорение работы с БД).
- Ленивая загрузка (Lazy Loading) для оптимизации запросов.

Для подключения domain-а к hibernate нужно:
перед классом
@Entity
@Table(name="...")

@Id // обозначаниe primary key
@GeneratedValue(strategy = GenerationType.IDENTITY) // стратегия задания primary key

### Связь one-one, one-many, many-many, как делается в джаве?
one-one:

one-many:

many-many:


### Как сделать общую таблицу для разных обьектов?


## Grpc

### Grpc
библиотека поддерживаемая для большого множества языков позволяющая общаться сервисам с помощью удалённого вызова процедур

Те для самих сервисов общение выглядит как простой вызов функций, которые могут возвращать в тч сложные объекты

Особенности:
1) protobuf
2) http2 - передача битов напрямую + независимые потоки данных внутри одного tcp соединения + HPACK (сжатие Header-ов)
3) стриминг данных

### protobuf и .proto
protobuf - библиотека, позволяющая сегенерировать функции общение между сервисами содержащие внутри себя:
1) сереализации и десереализацию
2) передачу данных

.proto - формат файлов, определяющих контракты функций, а так же их аргументы - возможно сложные объекты

### @GrpcService
Аннотация, что помечает gRPC сервисы, которые должны быть зарегистрированы Spring Boot-ом как gRPC сервера

```java
@GrpcService
public class ReportGrpcController extends ReportServiceGrpc.ReportServiceImplBase {
  /* just base class with any fields */

  @Override
  public void someGrpcMethod(someRequest request,
                             StreamObserver<someStreamResponse> responseObserver,
                             someResponse responseBase) {
  }
}
```

### Как сгенерировать серверные и клиентские сервисы для общения
В java удобно через добавление в build
```kts
id("com.google.protobuf") version "0.9.4"

implementation("net.devh:grpc-server-spring-boot-starter:2.15.0.RELEASE")
implementation("io.grpc:grpc-protobuf:1.54.0")
implementation("io.grpc:grpc-stub:1.54.0")
compileOnly("org.apache.tomcat:annotations-api:6.0.53")

protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:3.22.0"
  }
  plugins {
    create("grpc") {
      artifact = "io.grpc:protoc-gen-grpc-java:1.54.0"
    }
  }
  generateProtoTasks {
    all().forEach { task ->
      task.plugins {
        create("grpc")
      }
    }
  }
}
```

### Как написать сообщение в @tg
1) надо взять библиотеку org.telegram.telegrambots
2) отнаследоваться от TelegramLongPollingBot
3) имлементировать getBotUsername(), getBotToken(), onUpdateReceived(Update update)
4) у полученного класса есть метод execute(...) в который можно передавать объекты SendMessage
