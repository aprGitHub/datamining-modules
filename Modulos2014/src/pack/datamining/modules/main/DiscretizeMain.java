package pack.datamining.modules.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import pack.datamining.modules.filters.Discretization;
import pack.datamining.modules.io.LoaderSaver;
import pack.datamining.modules.util.Strings;
import weka.core.*;

public class DiscretizeMain {

	public static void main(String[] args) 
	{
		String LOG_TAG= DiscretizeMain.class.getSimpleName().toString();
		
		Instances instances = LoaderSaver.getMyLoader().loadArff(args[0]);
		int intervalos = 10;
		int pos = instances.numAttributes() - 1;
		
		if (args.length > 1 && args.length < 4)
		{ 
			if (args[1] != null && args[2] != null)
			{
				if (args[1].equals("-I"))
				{
					System.out.println("I");
					intervalos = Integer.valueOf(args[2]);
				}
				
				if (args[1].equals("-C"))
				{System.out.println("C");
					pos = Integer.valueOf(args[2]);
				}
			}
		}
		if (args.length > 4 && args.length < 6)
		{
				if (args[1].equals("-I"))
				{
					intervalos = Integer.valueOf(args[2]);
				}
				
				if (args[1].equals("-C"))
				{
					pos = Integer.valueOf(args[2]);
				}
				if (args[3] != null && args[4] != null)
				{
					if (args[3].equals("-I"))
					{
						intervalos = Integer.valueOf(args[4]);
					}
					if (args[3].equals("-C"))
					{
						pos = Integer.valueOf(args[4]);
					}
				}

		}
		
		try 
		{
			Instances filteredInstances = Discretization.getDiscretized(instances,intervalos ,pos);
			LoaderSaver.getMyLoader().saveInstances(filteredInstances, args[0] + ".DiscretizedClass.arff" );
		} 
		catch (Exception e) 
		{
			Logger.getLogger(LOG_TAG).log(Level.SEVERE, Strings.MSG_ERROR_OUTLIERS+e.getMessage());
		}
	

	}
	}
