#include <iostream>
#include <string>

using namespace std;

// output string word by color
void print_color_word(string color, string word);

int main()
{
	print_color_word("red", "Hello! this is show color ...");
	print_color_word("green", "Hello! I am Green!\n");


	return 0;
}

void print_color_word(string color, string word)
{
	int clr;
	string head, tail, show;

	if(color == "red")
		clr = 1;
	if(color == "green")
		clr = 2;
	switch(clr){
		case 1:
			head = "\033[31m";
			tail = "\033[0m";
			show = head + word + tail;
			break;
		case 2:
			head = "\033[32m";
			tail = "\033[0m";
			show = head + word + tail;
			break;
		default:
			show = word;

	}
	cout << show << endl;
}
