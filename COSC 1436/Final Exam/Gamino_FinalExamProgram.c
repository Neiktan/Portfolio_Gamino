#include <stdio.h>

// functions
float calculate_Manager();
float calculate_Hour_Worker();
float calculate_Commision();
float calculate_Taxes(float gross_Pay);

int main(){
    // arrays
    int employees[7];
    float taxes[7];
    float net_Pay[7];
    float gross_Pay[7];

    // variable
    int i;
    int pay_Code;
    float total_Company_pay = 0;
    int id;

    for (i = 0; i < 7; i++){

        printf("\nEnter a ID for the employee: "); //prompt to input the ID 
        scanf("%d", &id);

        printf("\nATENTION!!: The Paycode is only between 1 - 3\nEnter the code of the employee: "); // prompt to input the paycode and using it on switch
        scanf("%d", &pay_Code);

        switch (pay_Code){//gross pay will be after of the function works for 1 value in the array
        case 1:
            gross_Pay[i] = calculate_Manager();
            break;
        case 2:
            gross_Pay[i] = calculate_Hour_Worker();
            break;
        case 3:
            gross_Pay[i] = calculate_Commision();
            break;
        default:
            printf("\n-----------------------------------------------------------------");
            printf("\nPlease enter a code number that should be between from 1 to 3\n");
            printf("-----------------------------------------------------------------\n");
            i--; // to reduce a value on the for
            continue;
        }

        // calculate pays
        employees[i] = id; // to use the id into the array of the employees
        taxes[i] = calculate_Taxes(gross_Pay[i]);
        net_Pay[i] = gross_Pay[i] - taxes[i]; // to get net pay, take from the gross pay the taxes, grosspay - taxes
        total_Company_pay += net_Pay[i];      // all the money that the company gives to the employees
    }

    // outputs
    //title and 1 just time to print it
    printf("\n---------------------------------------------------------------------\n");
    printf("\nID    |    Gross Pay    |    Taxes    |    Net Pay     |\n");
    for (i = 0; i < 7; i++){ //for in output to repeat just the result of the employees salary
        printf("\n%2d    | $%10.2f     | $%8.2f   | $%9.2f     |\n", employees[i], gross_Pay[i], taxes[i], net_Pay[i]);
    }
    printf("\nTotal Company Payroll: $%.2f\n", total_Company_pay); //total money spend it on employees salary by the company 
    printf("\n---------------------------------------------------------------------\n");

    return 0;
}

// function for Manager
float calculate_Manager(){
    float anual, salary;
    printf("\nEnter the annual salary for the manager: $");
    scanf("%f", &anual);

    salary = anual / 52; //52 week per year divided between the money of the year

    return salary;
}

// function for hour worker
float calculate_Hour_Worker(){
    int  hours;
    float hourly_Rate, gross_Pay;

    printf("\nHow many hours did the employee work?: "); //prompt to get the information of hours the employee worked
    scanf("%d", &hours);

    printf("\nEnter the hourly wage for this employee: "); //prompt to gfet the information of the rate that the employee has in the company
    scanf("%f", &hourly_Rate);

    if (hours < 40){
        //regular hours
        gross_Pay = hours * hourly_Rate;
    }
    else{
        //overtime
        gross_Pay = (40 * hourly_Rate) + ((hours - 40) * hourly_Rate * 1.5); 
    }

    return gross_Pay;
}

// function for commision worker
float calculate_Commision(){
    int base_Salary = 375;
    float gross_Sales = 0.072, gross_Pay, sales;

    printf("\nEnter the gross weekly sales for the employee: $"); //prompt to get the information of the gross weekly on sales of the company
    scanf("%f", &sales);

    gross_Pay = base_Salary + (sales * gross_Sales); //gross weekly sales * %7.2

    return gross_Pay;
}

// function for taxes
float calculate_Taxes(float gross_Pay){
    return gross_Pay * 0.08625; //the gross pay of each salary of the employee by %8.625
}
