'''
Created on Jul 18, 2016

@author: gaston
'''

def read_file(path):
    nums = []
    f = open(path)
    for line in f:
        nums.append(int(line))
    return nums

def merge_and_count_split_inversions(B, C):
    i=0
    j=0
    D = []
    split_inversions = 0
    n = len(B) + len(C)
    for k in range(0,n):
        if(j == len(C) or (i < len(B) and B[i] < C[j])):
            D.append(B[i])
            i += 1
        else:
            split_inversions += len(B[i:])
            D.append(C[j])
            j += 1
    return(D,split_inversions)
    
    

def sort_and_count_inversions(array):
    n = len(array)
    if(n == 1):
        return (array,0)
    else:
        
        (sorted_first_half, first_half_inversions) = sort_and_count_inversions(array[:n/2])
        (sorted_second_half, second_half_inversions) = sort_and_count_inversions(array[n/2:])
        (merged_array, split_inversions) = merge_and_count_split_inversions(sorted_first_half, sorted_second_half)
        
    return (merged_array, first_half_inversions + second_half_inversions + split_inversions)
        

array = read_file("data/IntegerArray.txt")

(sorted_array, inversions) = sort_and_count_inversions(array)

print inversions



