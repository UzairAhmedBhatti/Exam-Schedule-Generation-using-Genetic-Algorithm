# Exam-Schedule-Generation-using-Genetic-Algorithm
Introduction And Methodology
● The input data class reads data from registration, room capacity, general.
● Gene represents one block of timetable(list of courses in one slot).
● The chromosome class is for creation of chromosomes and random
numbers.Chromosome is represented as a 2D Matrix with row as no of days x
column as slots(A Complete Timetable).The class contains a CreateChromosome
function which randomly initialise the chromosome.
● Fitness function, crossovers and mutation is also implemented here.Chromosome
Comparator just compares two chromosomes by taking the difference of their
fitnesses.
● In our main program, firstly input is taken from files followed by the creation of
chromosomes.Population of 100 chromosomes is created in which each
chromosome is randomly generated.
● Next Step includes calculation of each chromosomes fitness and on the basis of
fitness,population is sorted
● Target is set and then the Genetic Algorithm is applied including creating new
generations and doing mutations and crossovers.
● Selection criteria is elitism meaning 10% of fittest chromosomes in a population are
automatically added to the next generation while from the remaining 90% ,we select
the parents for the crossover to generate new offspring for the next
generation.Thus,mutation is introduced to maintain randomness in population and
crossover is achieved through the combination of two parent chromosomes genes.
● Finally the new population is created and for this fitness is calculated for each
chromosome and the process is repeated until we get one such
chromosome(TimeTable) which has the targeted fitness.
● Our fitness function goes by all numbers of days for every slot and assigns points on
the basis of the following conditions.
1. Timetable must have all courses.
2. No of students with 2 exams in the same slot should be minimum.
3. No student has more than two exams in one slot.
4. No of students giving exams in one slot should not be more than total
capacity.
5. No of students with 2 exams in consecutive slots should be minimum
6. No student has no exams in more than 2 consecutive slots.
Our code is successfully creating generations along with changing fitness along with
successful crossover,mutation and selection. However after approximately 200 generations
the value of fitness functions becomes constant.The fitness value increases with the
increasing generations.Once fitness becomes constant,we take the final generations top
fitness chromosome and then apply local search.In local search we simply create new
childs from this chromosome and continue to create generations after generations.For
each child we calculate fitness and add it to the current generation.Every child is created by
randomly changing the slots of the Chromosome supplied by GA.Moreover,as fitness
becomes constant then again in local search we return the top Chromosome closer to our
target fitness.
We have made two fitness functions and attached are the experiment results of the fitness
function one. The generation and fitness are changing but due to the crossover of two
parents slots become empty and the schedule could not be found.
In the other fitness function, we abide by the condition that total courses in slots equals the
total number of courses in the data and that no course is repeated. However, as a result
our fitness does not show significant change but on the other side we are successful in
getting a schedule.
Along with this we have also implemented the local search.
2
We have applied GA and local search but somehow due to time constraint,we feel that the
fitness function needs to be reevaluated along with local search code.However,we have
tried to deliver our best.
