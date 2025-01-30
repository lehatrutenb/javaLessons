## Интерфейс
### хотим контракт для объектов на их функциональность
Мы не можем создать его объект те new Interface()
```java
interface Interface {
    public void method();
}

class Class implements Interface {
    public void method() {
        // some logic
    }
}

class Main {
    public static void main(String[] args) {
        Class sc = new Class();
        sc.method();
        Interface iObj = sc;
        iObj.method();
    }
}
```


## Абстрактный класс

### хотим иметь интерфейс, но также уже реализованную общую логику/есть общий объект - хотим описать частные случаи
Мы не можем создать его объект те new BaseAbstractClass()
```java
abstract class BaseAbstractClass {
    public abstract void notImplIndividualMethod();
    private type f1;
    public void implBaseMethod() {
        // some logic
    }
}

class SubClass extends BaseAbstractClass {
    public void notImplIndividualMethod() {
        // some logic
    }
}

class Main {
    public static void main(String[] args) {
        SubClass sc = new SubClass();
        sc.implBaseMethod();
        sc.notImplIndividualMethod();
        BaseAbstractClass bACObj = sc;
        bACObj.implBaseMethod();
        bACObj.notImplIndividualMethod();
    }
}
```

## Spring
### Тесты
чтобы объекты пересоздавались заново перед тестом нужно помимо @Test писать @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)