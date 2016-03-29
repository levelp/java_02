//-->
#include <iostream>

using namespace std;

int main(){
  int a = 3, b = 10;

  a = 3; b = 10;
  cout << "a ^= b;" << endl;
  a ^= b;
  cout << "b ^= a;" << endl;
  b ^= a;
  cout << "a ^= b;" << endl;
  a ^= b;
  cout << "a = " << a << "   b = " << b << endl;

  a = 3; b = 10;
  cout << "a ^= b ^= a ^= b;" << endl;
  a ^= b ^= a ^= b;

  cout << "a = " << a << "   b = " << b << endl;

  a = 3; b = 10;
  cout << "a ^= (b ^= (a ^= b));" << endl;
  a ^= (b ^= (a ^= b));

  cout << "a = " << a << "   b = " << b << endl;

  return 0;
}
//<--