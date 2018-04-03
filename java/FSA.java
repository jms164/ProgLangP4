import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FSA
{
	private String fileName; // name of file holding FSA
	private int numStates; // number of states in FSA
	private String[] transitionNames; // labels of the state transitionNames in FSA
	private char[][] matrix; // matrix representation of the FSA
	private int startState; // the starting state of the FSA
	private int[] finalStates; // the final states of the FSA
	private boolean success; // tells whether or not the FSA was set up successfully
	
	
	public FSA(String fileName)
	{
		setFileName(fileName);
		setSuccess(true);
		readFile();
	}
	
	// accessor/mutator methods
	public String getFileName()
	{
		return fileName;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public int getNumStates()
	{
		return numStates;
	}
	
	public void setNumStates(String strNumStates)
	{
		this.numStates = Integer.parseInt(strNumStates);
	}
	
	public String[] getTransitionNames()
	{
		return transitionNames;
	}
	
	public void setTransitionNames(String strTransitionNames)
	{
		String transitionNames[] = strTransitionNames.split(",");
		
		for(int i = 0; i < transitionNames.length; i++)
		{
			if(transitionNames[i].isEmpty())
			{
				System.out.println("error: FSA file not properly formatted. improper transition name format");
				setSuccess(false);
				return;
			}
		}
		
		this.transitionNames = transitionNames;
	}
	
	public char[][] getMatrix()
	{
		return matrix;
	}
	
	public void setMatrix(String strTransitions)
	{
		String arrTransitions[] = strTransitions.split(",");
		char matrix[][] = new char[arrTransitions.length][arrTransitions.length];
		String transition;
		int startNode;
		int endNode;
		char transitionName;
		
		for(int i = 0; i < arrTransitions.length; i++)
		{
			transition = arrTransitions[i];
			startNode = transition.charAt(1) - '0';
			if((startNode < 0) || (startNode > 9))
			{
				System.out.println("error: FSA file not properly formatted. improper state transition format");
				setSuccess(false);
				return;
			}
			
			endNode = transition.charAt(3) - '0';
			if((endNode < 0) || (endNode > 9))
			{
				System.out.println("error: FSA file not properly formatted. improper state transition format");
				setSuccess(false);
				return;
			}
			
			transitionName = transition.charAt(5);
			if((transitionName < 97) || (transitionName > 122))
			{
				System.out.println("error: FSA file not properly formatted. improper state transition format");
				setSuccess(false);
				return;
			}
			
			matrix[startNode][endNode] = transitionName;
		}
		
		this.matrix = matrix;
	}
	
	public int getStartState()
	{
		return startState;
	}
	
	public void setStartState(String strStartState)
	{
		int startState = Integer.parseInt(strStartState);
		if(startState > getNumStates())
		{
			System.out.println("error: FSA file not properly formatted. start state does not exist");
			setSuccess(false);
			return;
		}
		
		this.startState = startState;
	}
	
	public int[] getFinalStates()
	{
		return finalStates;
	}
	
	public void setFinalStates(String strFinalStates)
	{
		String arrFinalStates[] = strFinalStates.split(",");
		int finalStates[] = new int[arrFinalStates.length];
		
		for(int i = 0; i < arrFinalStates.length; i++)
		{
			finalStates[i] = Integer.parseInt(arrFinalStates[i]);
			
			if(finalStates[i] > getNumStates())
			{
				System.out.println("error: FSA file not properly formatted. end state does not exist");
				setSuccess(false);
				return;
			}
		}
		
		this.finalStates = finalStates;
	}
	
	public boolean isSuccess()
	{
		return success;
	}
	
	public void setSuccess(boolean success)
	{
		this.success = success;
	}
	
	
	private void readFile()
	{
		Scanner file;
		String buffer = "";
		String tokens[];
		
		try
		{
			file = new Scanner(new File(getFileName()));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("error: FSA file not found");
			setSuccess(false);
			return;
		}
		
		while(file.hasNext())
		{
			buffer += file.next();
		}
		
		if((tokens = tokenize(buffer)) != null)
		{
			setNumStates(tokens[0]);
			setTransitionNames(tokens[1]);
			setMatrix(tokens[2]);
			setStartState(tokens[3]);
			setFinalStates(tokens[4]);
		}
		
		if(isSuccess())
		{
			System.out.println("FSA created successfully!\n");
		}
	}
	
	
	private String[] tokenize(String buffer)
	{
		String tokens[] = buffer.split(";");
		
		for(int i = 0; i < tokens.length; i++)
		{
			if(tokens[i].isEmpty() || (tokens.length != 5))
			{
				System.out.println("error: FSA file not properly formatted");
				setSuccess(false);
				return null;
			}
		}
		
		return tokens;
	}
}