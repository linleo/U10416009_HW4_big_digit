import java.util.ArrayList;

public class BigDigit 
{	
	BigDigit() {}
	
	void parseStrToInt(String numberStr1, String numberStr2, String opereator)
	{
		ArrayList<Integer> number1 = new ArrayList<>();
		ArrayList<Integer> number2 = new ArrayList<>();
		ArrayList<Integer> answer = new ArrayList<>();
		
		int i;
		boolean isNum1Nagetive = false;
		boolean isNum2Nagetive = false;
		for (i = 0; i < numberStr1.length(); i++)
		{
			if (numberStr1.charAt(i) == '-')
			{
				isNum1Nagetive = true;
				continue;
			}
			number1.add(numberStr1.charAt(i) - '0');
		}
		for (i = 0; i < numberStr2.length(); i++)
		{
			if (numberStr2.charAt(i) == '-')
			{
				isNum2Nagetive = true;
				continue;
			}
			number2.add(numberStr2.charAt(i) - '0');
		}
		
		if (opereator.equals("+"))
		{
			answer = addition(number1, number2, isNum1Nagetive, isNum2Nagetive);
			for (i = 0; i < answer.size(); i++)
			{
				System.out.print(answer.get(i));
			}
		}
     	else if (opereator.equals("-"))
		{
			answer = subtraction(number1, number2, isNum1Nagetive, isNum2Nagetive, false);
			boolean findNonZero = false;
			for (i = 0; i < answer.size(); i++)
			{		
				if (answer.get(i) != 0)
					findNonZero = true;
				if (findNonZero)
					System.out.print(answer.get(i));
			}
			if (!findNonZero)
				System.out.print("0");
		}
		else if (opereator.equals("*"))
		{
			answer = multiplication(number1, number2, isNum1Nagetive, isNum2Nagetive, false);
			boolean findNonZero = false;
			for (i = 0; i < answer.size(); i++)
			{		
				if (answer.get(i) != 0)
					findNonZero = true;
				if (findNonZero)
					System.out.print(answer.get(i));
			}
			if (!findNonZero)
				System.out.print("0");
		}
	}
	
	void parseToSameDigit(ArrayList<Integer> number1, ArrayList<Integer> number2)
	{
		int digitOffset1, digitOffset2;
		int i;
		digitOffset1 = number1.size()-number2.size();
		digitOffset2 = number2.size()-number1.size();
		if (number1.size() > number2.size())
		{
			for (i = 0; i < digitOffset1; i++)
			{
				number2.add(0, 0);
			}
		}
		else if (number2.size() > number1.size())
		{
			for (i = 0; i < digitOffset2; i++)
			{
				number1.add(0, 0);
			}
		}
	}

	ArrayList<Integer> addition(ArrayList<Integer> number1, ArrayList<Integer> number2, boolean isNum1Nagetive, boolean isNum2Nagetive)
	{
		ArrayList<Integer> answer = new ArrayList<>();
		
		if (!isNum1Nagetive && isNum2Nagetive)
		{
			return subtraction(number1, number2, false, false, false);
		}
		else if (isNum1Nagetive && !isNum2Nagetive)
		{
			return subtraction(number2, number1, false, false, false);
		}
		parseToSameDigit(number1, number2);
		int i, carry = 0;
		for (i = number1.size()-1; i > -1; i--)
		{
			if ((number1.get(i) + number2.get(i) + carry) > 9)
			{
				answer.add(0, ((number1.get(i) + number2.get(i) + carry) % 10));
				carry = 1;
				if (i == 0)
					answer.add(0,1);
			}
			else 
			{
				answer.add(0, (number1.get(i) + number2.get(i) + carry));
				carry = 0;
			}
		}
		if (isNum1Nagetive && isNum2Nagetive)
			System.out.print("-");
		return answer;
	}

	ArrayList<Integer> subtraction(ArrayList<Integer> number1, ArrayList<Integer> number2, boolean isNum1Nagetive, boolean isNum2Nagetive, boolean addMinuSign)
	{
		ArrayList<Integer> answer = new ArrayList<>();
		
		if (!isNum1Nagetive && isNum2Nagetive)
		{
			return addition(number1, number2, false, false);
		}
		else if (isNum1Nagetive && !isNum2Nagetive)
		{
			return addition(number1, number2, true, true);
		}
		else if (isNum1Nagetive && isNum2Nagetive)
			return subtraction(number2, number1, false, false, false);
		else if (!isGreaterOrEqual(number1, number2))
			return subtraction(number2, number1, false, false, true);
		parseToSameDigit(number1, number2);
		int i, borrow = 0;
		for (i = number1.size()-1; i > -1; i--)
		{
			if ((number1.get(i) - number2.get(i) - borrow) < 0)
			{
				answer.add(0, ((number1.get(i) - number2.get(i) + 10) - borrow));
				borrow = 1;
			}
			else 
			{
				answer.add(0, (number1.get(i) - number2.get(i) - borrow));
				borrow = 0;
			}
		}
		if (addMinuSign)
			System.out.print("-");
		return answer;
	}
	
	boolean isGreaterOrEqual(ArrayList<Integer> number1, ArrayList<Integer> number2)
	{
		parseToSameDigit(number1, number2);
		int i;
		for (i = 0; i < number1.size(); i++)
		{
			if (number1.get(i) > number2.get(i))
				return true;
			else if (number1.get(i) < number2.get(i))
				return false;
		}
		return true;
	}
