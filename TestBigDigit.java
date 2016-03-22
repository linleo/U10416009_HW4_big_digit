import java.util.Scanner;

public class TestBigDigit
{
	public static void main(String[] args)
	{		
		Scanner input = new Scanner(System.in);
		BigDigit bigDigit = new BigDigit();
		System.out.print("Enter the first number : ");
		String numberStr1 = input.next();
		while ((numberStr1.substring(0,1)).matches("[[^0-9]&&[^-]]") || !((numberStr1.substring(1,numberStr1.length())).matches("[0-9]*")))
		{
			System.out.println("Input only digit or miuns sign, and miuns sign should be prefix");
			System.out.print("Enter again : ");
			numberStr1 = input.next();
		}
		System.out.print("Enter the second number : ");
		String numberStr2 = (input.next());
		while ((numberStr2.substring(0,1)).matches("[[^0-9]&&[^-]]") || !((numberStr2.substring(1,numberStr2.length())).matches("[0-9]*")))
		{
			System.out.println("Input only digit or miuns sign, and miuns sign should be prefix");
			System.out.print("Enter again : ");
			numberStr2 = input.next();
		}
		System.out.print("Enter the opereator to do operation (ex:+,-,*,/) : ");
		String opereator = input.next();
		bigDigit.parseStrToInt(numberStr1, numberStr2, opereator);
	}
}
