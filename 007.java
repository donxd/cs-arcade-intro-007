boolean almostIncreasingSequence(int[] sequence) {

    Integer positionElementNoSequence = getPositionElementNoSequence( sequence );
    
    if ( positionElementNoSequence != null ){
        int [] leftSequence = getLeftSequence( sequence, positionElementNoSequence );
        int [] rigthSequence = getRightSequence( sequence, positionElementNoSequence );
    
        return validOptionsSequence( leftSequence, rigthSequence );
    }
    
    return true;
}

int getPositionElementNoSequence ( int [] sequence ){
    Integer positionElementNoSequence = null;
    Integer limitElement = null;
    int curretElement;
    for ( int i = 0; i < sequence.length; i++ ){
        curretElement = sequence[ i ];
        if ( limitElement == null || limitElement < curretElement ){
            limitElement = sequence[ i ];
        } else {
            positionElementNoSequence = i;
            break;
        }
    }
    
    return positionElementNoSequence;
}

int [] getLeftSequence ( int [] sequence, int positionElementNoSequence ){
    return Arrays.copyOfRange( sequence, 0, positionElementNoSequence );
}

int [] getRightSequence ( int [] sequence, int positionElementNoSequence ){
    return Arrays.copyOfRange( sequence, positionElementNoSequence, sequence.length );
}

boolean validOptionsSequence ( int [] leftSequence, int [] rigthSequence ){
    
    if ( leftSequence.length == 1 ) return validOptionSequence( rigthSequence );
    if ( rigthSequence.length == 1 ) return validOptionSequence( leftSequence );
    
    boolean res = false;
    if ( leftSequence[ leftSequence.length-2 ] < rigthSequence[ 0 ] ){
        int [] leftRemoveSequence = getLeftRemoveSequence( leftSequence, rigthSequence );
        res = validOptionSequence( leftRemoveSequence );
    }
    
    if ( !res && leftSequence[ leftSequence.length-1 ] < rigthSequence[ 1 ] ){
        int [] rightRemoveSequence = getRightRemoveSequence( leftSequence, rigthSequence );
        res = validOptionSequence( rightRemoveSequence );
    }
    
    return res;
}

int [] getLeftRemoveSequence( int [] leftSequence, int [] rigthSequence ){
    int [] fragment = getLeftSequence( leftSequence, leftSequence.length-1 );
        
    return getFusionSequences( fragment, rigthSequence );
}

int [] getRightRemoveSequence( int [] leftSequence, int [] rigthSequence ){
    int [] fragment = getRightSequence( rigthSequence, 1 );
        
    return getFusionSequences( leftSequence, fragment );
}

int [] getFusionSequences ( int [] initialSequence, int [] finalSequence ){
    int [] fusion = new int [ initialSequence.length + finalSequence.length ];
    System.arraycopy( initialSequence, 0, fusion, 0, initialSequence.length );
    System.arraycopy( finalSequence, 0, fusion, initialSequence.length, finalSequence.length);
    
    return fusion;
}

boolean validOptionSequence ( int [] sequence ){
    boolean res = true;
    
    for ( int i = 1; i < sequence.length; i++ ){
        if ( sequence[ i ] <= sequence[ i-1 ] ){
            res = false;
            break;
        }
    }
    
    return res;
}
