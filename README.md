# Calculate_Hydrogen_bond
Calculate number of hydrogen bond after adding hydrogen to .cif(PDB FIle Format) on bases of distance constraint through the Molecular Simulation files.
All of these files work in coordination. First, it will ask for the starting  PDB file and then it will show you a number of ligands present in your PDB file. then it will add hydrogen to PDB file and create a file with the file name extended by  _H.pdb which represents Hydrogen added to file.
1. how it adds hydrogen look into repository AddH.
2. After that, it asks for a number of Molecular Simulation files you have. then it automatically picks file one by one and calculates the no. of hydrogen bond present at a particular time. Then show the result in another file.
Future Plan-
1. After making changes in the program we can make it feasible to calculate hydrogen bond on angle constraint as well.
2. After calculating no. of hydrogen bond for all the Molecular Simulation files we can plot it on graph no. of hydrogen bond v/s Molecular Simulation time interval.
3. It would work for whole protein as well as all ligands to calculate hydrogen bond after making some modification in the program.
