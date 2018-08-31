import java.math.BigInteger;

public class IntToWord
{
  private static final int SPACE = 32; //space starting point
  private static final int LOWERCASE = 122; //lowercase ending point
  private static final boolean DEBUGGING = false;

  public static void main(String [] args)
  {
    BigInteger mysterWord = new BigInteger("8710110899111109101321161113210912132711051167211798");
    BigInteger temp = mysterWord;
    String retVal = "";
    Integer modBy = 100;
    int cycleCount = 0;

    while ( temp.toString().length() > 1)
    {
      if(DEBUGGING)
      {
        System.out.println("The temp is " + temp);
      }
      //get last 2 or last 3 digits, depending on what modBy is
      BigInteger endingDigits = temp.mod(new BigInteger(modBy.toString()));

      if(DEBUGGING)
      {
        System.out.println("The ending digits are " + endingDigits.toString());
      }

      if(isValid(endingDigits))
      {
        if(DEBUGGING)
        {
          System.out.println("Match found!");
        }
        //if match, append to retVal
        char toChar = (char)endingDigits.intValue();
        String charToString = new Character(toChar).toString();

        if(DEBUGGING)
        {
          System.out.println("will concat " + charToString + " to retVal");
        }

        retVal = charToString.concat(retVal);
        temp = temp.divide(new BigInteger(modBy.toString()));

        if(DEBUGGING)
        {
          System.out.println("setting temp to " + temp.toString());
        }
        //set modBy and cylce count to default
        modBy = 100;
        cycleCount = 0;

      } else //check if last 3 digits is a match, if not move on to next set of endingDigits
      {
        if(DEBUGGING)
        {
          System.out.println("The ending digits were invalid");
        }

        cycleCount++;

        if(DEBUGGING)
        {
          System.out.println(cycleCount);
        }

        cycleCount = cycleCount % 2;

        if(cycleCount == 1) //try modding by 1000
        {
          modBy = 1000;

        } else //drop last 3 digits, no matching in last 2 or last 3
        {
          temp = temp.divide(new BigInteger("1000"));

          if(DEBUGGING)
          {
            System.out.println("setting temp to " + temp.toString());
          }

          modBy = 100; //reset modBy
        }

        if(DEBUGGING)
        {
          System.out.println("Will modded by " + modBy + " next cycle");
        }
      }
    }
    if(DEBUGGING)
    {
      if(retVal.length() == 0)
      {
        System.out.println("No mysterWords were able to be concatenated");
      }
    }

    System.out.println("The mystery string is: " + retVal);
  }

  private static boolean isValid(BigInteger given)
  {
    return given.intValue() >= SPACE && given.intValue() <= LOWERCASE;
  }
}
