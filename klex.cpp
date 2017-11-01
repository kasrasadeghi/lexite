#include <regex>
#include <string>
#include <fstream>
#include <iostream>

using namespace std;

string read_file(string filename) {
  ifstream ifs(filename);
  return string(istreambuf_iterator<char>(ifs),
                istreambuf_iterator<char>());
}

int main() {

  cout << read_file("klex.cpp");
  // open the file
  // read the contents into a string
  // send it to the lexer
  // receive the vector of tokens
  // print them out, one by one
}
