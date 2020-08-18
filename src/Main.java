
import org.apache.jena.graph.compose.Union;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.Filter;
import org.apache.thrift.TUnion;

import java.io.FilterReader;
import java.util.Scanner;
//import org.apache.log4j.variable.NullAppender;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String args[])
    {
        Scanner sc =new Scanner(System.in);
        String search;
        System.out.println("Enter a value you want to search");
        search = sc.nextLine();
      //  System.out.println("Enter the Ram");
        //Ram = sc.nextLine();
        //org.apache.log4j.BasicConfigurator.configure(new NullAppender());
        sparqlTest();
    }
    static void sparqlTest() {




        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        Model model = org.apache.jena.util.FileManager.get().loadModel("/Users/shubhambaranwal/IdeaProjects/jena-app/src/data.rdf");

       // String queryString =
         //       "PREFIX my:<http://purl.org/laptop-sa#>"+
           //             "SELECT * WHERE { " +
             //           "?Laptop my:RAM?x ."+
               //         "}";



       /* String queryString =
                "PREFIX my:<http://purl.org/laptop-sa#>"+
                       "SELECT * WHERE {" +
                        "?Laptop my:Processor?x ." +
                        "?Laptop my:RAM?y .}";*/



      /* String queryString =
                "PREFIX my:<http://purl.org/laptop-sa#>"+
                        "SELECT ?Laptop?RAM WHERE {" +
                        "?Laptop my:RAM?search." +
                        //"?Laptop my:Name?x ."+
                       // "FILTER(bound(?search))"+
                        "}";*/

        String queryString =
                "PREFIX my:<http://purl.org/laptop-sa#>"+
                "SELECT * WHERE{" +
                "?Laptop my:Model_No?x.}"; 

        Query query = QueryFactory.create(queryString);
        QueryExecution qexec  = QueryExecutionFactory.create(query,model);

        try {

            ResultSet results = qexec.execSelect();
            while(results.hasNext())
            {
                QuerySolution soln = results.nextSolution();
                Literal name = soln.getLiteral("x");
                //Literal name1 = soln.getLiteral("y");

                System.out.println(name);
               // System.out.println(name1);
            }
        }finally
        {
            qexec.close();
        }



    }
}
