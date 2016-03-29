//
// Исключения в Java
// ----------------
// Бывают двух видов:
// * Наследники от класса **Exception**
//  надо указывать throws в цепочке вызовов.
// * Наследники от класса **RuntimeException**
//  не надо указывать throws.
package levelp;

//-->
// Любое значение X
public class AnyXException extends RuntimeException {
}
//<--
