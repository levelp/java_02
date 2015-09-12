Стек и очередь на Java
======================

Стек: Stack<T>
* push добавить на вершину
* pop получить с вершины и удалить
* isEmpty() стек пуст?

``` java
 Stack<Integer> stack = new Stack<>();
 stack.push(1);
 stack.push(2);
 stack.push(3);
 assertEquals(3, stack.pop().intValue());
 assertEquals(2, stack.pop().intValue());
 assertEquals(1, stack.pop().intValue());
```

Очередь: Queue<T>
* put добавить в начало очереди
* get получить из конца очереди и удалить
* isEmpty() стек пуст?

``` java
Queue<String> queue = new Queue<>();
queue.put("Hello");
queue.put("world");
assertEquals("Hello", queue.get());
assertEquals("world", queue.get());
````