import ilog.concert.*;
import ilog.cplex.*;

public class exoTP {
       public static void main(String[] args) {
               calcul ();
       }

public static void calcul (){
    try {
          IloCplex simplexe = new IloCplex ();
        
          // declaration des coefficients de la fonction objectif
          double [][] D={{0,15,37,55,24,60,18,33,48,40,58,67},
                       {15,0,22,40,38,52,33,48,42,55,61,61},
                       {37,22,0,18,16,30,41,28,20,58,39,39},
                       {55,48,18,0,34,12,59,46,24,62,43,34},
                       {24,38,16,34,0,36,25,12,24,47,37,43},
                       {60,52,30,12,36,0,57,42,12,50,31,22},
                       {18,33,41,59,25,57,0,15,45,22,40,61},
                       {33,48,28,46,12,42,15,0,30,37,25,46},
                       {48,42,20,24,24,12,45,30,0,38,19,19},
                       {40,55,58,62,47,50,22,37,38,0,19,40},
                       {58,61,39,43,37,31,40,25,19,19,0,21},
                       {67,61,39,34,43,22,61,46,19,40,21,0},
                            };
            double [] H={15,10,12,18,5,24,11,16,13,22,19,20};

   // déclaration des Variables de décision de type reel
          IloNumVar [][] X = new IloNumVar [n][];

          for (int i=0;i<12;i++){
              X[i]= simplexe.boolVarArray(n)
             }

          IloNumVar [] Y = new IloNumVar [n];
          for (int j=0;j<12;j++){
              Y[j]= simplexe.boolVar()
             }
    // declaration de la fonction objectif
          IloLinearNumExpr objectif = simplexe.linearNumExpr();
    
    // Définition des coefficients de la fonction objectif
    for(int i=0;i<12;i++){
    	  for(int j=0;j<12;j++){
          double g=H[i]*D[i][j];
    		   objectif.addTerm(g,X[i][j]);
            }
        }
           
    // Définir le type d'otimisation de la fonction (max ou min )
           simplexe.addMinimize(objectif);


    // contrainte 1 : 1*X1 + 2*X2 <= 70
          IloLinearNumExpr contrainte_1 = simplexe.linearNumExpr();
           for(int j=0;j<12;j++){
              contrainte_1.addTerm(1, Y[i]);
                }

          simplexe.addLe(contrainte_1, 3);
     
     //deuxième contrainte
           IloLinearNumExpr contrainte_2 = simplexe.linearNumExpr();
           for(int i=0;i<12;i++){
              for(int j=0;j<12;j++){
                 contrainte_2.addTerm(1, X[i][j]);
                }
            }
           simplexe.addLe(contrainte_2, 1);

      


       simplexe.solve(); // lancer la resolution

      
      // Afficher des résultat
          System.out.println("Voici la valeur de la fonction objectif "+ simplexe.getObjValue());
          System.out.println( "la valeur de Y[] est de ");
           for(int j=0;j<12;j++){
              System.out.println( " Y"+j+ simplexe.getValue(Y[i]));
                  }
             System.out.println( "la valeur de X[][] est de ");     
            for(int i=0;i<12;i++){
            	for(int j=0;j<12;j++){
            		 System.out.println( " X "+i+j+ simplexe.getValue(X[i][j]));
           
            	}
            }
           } catch (IloException e){
                System.out.print("Exception levée " + e);
               }
    }
 }