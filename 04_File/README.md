<!-- doc.py -->
Чтение из файла
[src/test/java/SaveLoadTest.java](src/test/java/SaveLoadTest.java)

try{
//...
} catch (Exception ex){
out.close();
}
Point newPoint = new Point();
Получаем название класса
Получаем метаданные класса
JVM его загружает если ещё не загрузила
+ возвращает метаданные
Создаем экземпляр класса
Считываем значения всех полей класса
Включаем доступ к не-public полям
out.println(field.get(object).toString());
newPoint.x = in.nextDouble();
newPoint.y = in.nextDouble();
Получаем метаданные класса
В первой строчке название класса
Выводим значения всех полей
Включаем доступ к не-public полям
out.println(point.x);
out.println(point.y);
[src/test/java/TextFileSaveLoad.java](src/test/java/TextFileSaveLoad.java)

