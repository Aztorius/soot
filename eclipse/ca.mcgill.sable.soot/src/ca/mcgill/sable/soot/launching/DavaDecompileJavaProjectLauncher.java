/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 Jennifer Lhotak
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

package ca.mcgill.sable.soot.launching;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.action.*;

/**
 * Launches Soot with -f dava on all classes in output dir of project
 */
public class DavaDecompileJavaProjectLauncher extends SootProjectLauncher {

	
	public void run(IAction action) {
		
		super.run(action);

		setCmd();
		runSootDirectly();
		runFinish();
	}
	
	private void setCmd() {
		
		ArrayList commands = new ArrayList();
		commands.add("--"+LaunchCommands.SOOT_CLASSPATH);
		String cp = getClasspathAppend();
		Iterator it = getJavaProcessPath().iterator();
		while (it.hasNext()){
			cp = cp + getSootClasspath().getSeparator() + (String)it.next();
		}
		commands.add(cp);
		//getSootCommandList().addDoubleOpt("--"+LaunchCommands.SOOT_CLASSPATH, getSootClasspath().getSootClasspath()+getSootClasspath().getSeparator()+getProcess_path());
			
				/*StringBuffer classpath = new StringBuffer(LaunchCommands.SOOT_CLASSPATH);
				classpath.append(getSootClasspath().getSootClasspath());
				classpath.append(getSootClasspath().getSeparator());
				classpath.append(getProcessPath());

		
				String output_path = LaunchCommands.OUTPUT_DIR + getOutputLocation();
				
				StringBuffer cmd = new StringBuffer();
				cmd.append(classpath+" ");
				cmd.append(output_path+" ");*/
			
		commands.add("--"+LaunchCommands.OUTPUT_DIR);
		commands.add(getOutputLocation());
		
		//getSootCommandList().addDoubleOpt("--"+LaunchCommands.OUTPUT_DIR, getOutputLocation());
		getSootCommandList().addSingleOpt("--"+LaunchCommands.KEEP_LINE_NUMBER);
		getSootCommandList().addSingleOpt("--"+LaunchCommands.XML_ATTRIBUTES);
		getSootCommandList().addDoubleOpt("--"+LaunchCommands.SRC_PREC, "java");
		
		Iterator it2 = getJavaProcessPath().iterator();
		while (it2.hasNext()){
			commands.add("--"+LaunchCommands.PROCESS_PATH);
			commands.add((String)it2.next());
		}	
		//commands.add("--"+LaunchCommands.PROCESS_PATH);
		//commands.add(getProcess_path());
		//getSootCommandList().addDoubleOpt("--"+LaunchCommands.PROCESS_PATH, getProcess_path());
		getSootCommandList().addSingleOpt("--"+LaunchCommands.DAVA);
		
		getSootCommandList().addSingleOpt(commands);	
		/*StringBuffer classpath = new StringBuffer(LaunchCommands.SOOT_CLASSPATH);
		classpath.append(getSootClasspath().getSootClasspath());
		classpath.append(getSootClasspath().getSeparator());
		classpath.append(getProcess_path());
	
		
		String output_path = LaunchCommands.OUTPUT_DIR+getOutputLocation();
				
		StringBuffer cmd = new StringBuffer();
		cmd.append(classpath+" ");
		cmd.append(output_path+" ");
		cmd.append(LaunchCommands.PROCESS_PATH+getProcess_path()+" ");
		cmd.append(LaunchCommands.DAVA);
		
	  	return cmd.toString();*/
	}

}
