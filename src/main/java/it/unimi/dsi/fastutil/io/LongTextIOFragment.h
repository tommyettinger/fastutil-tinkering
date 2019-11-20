/* Generic definitions */


#define PACKAGE it.unimi.dsi.fastutil.longs
#define VALUE_PACKAGE it.unimi.dsi.fastutil.objects
/* Assertions (useful to generate conditional code) */
#define KEY_CLASS_Long 1
 #define KEYS_PRIMITIVE 1
#define VALUE_CLASS_Object 1
 #define VALUES_REFERENCE 1
/* Current type and class (and size, if applicable) */
#define KEY_TYPE long
#define VALUE_TYPE Object
#define KEY_CLASS Long
#define VALUE_CLASS Object
#if KEYS_REFERENCE
#define KEY_GENERIC_CLASS K
#define KEY_GENERIC_TYPE K
#define KEY_GENERIC <K>
#define KEY_GENERIC_WILDCARD <?>
#define KEY_EXTENDS_GENERIC <? extends K>
#define KEY_SUPER_GENERIC <? super K>
#define KEY_GENERIC_CAST (K)
#define KEY_GENERIC_ARRAY_CAST (K[])
#define KEY_GENERIC_BIG_ARRAY_CAST (K[][])
#define SUPPRESS_WARNINGS_KEY_UNCHECKED @SuppressWarnings("unchecked")
#define SUPPRESS_WARNINGS_KEY_RAWTYPES @SuppressWarnings("rawtypes")
#define SUPPRESS_WARNINGS_KEY_UNCHECKED_RAWTYPES @SuppressWarnings({"unchecked","rawtypes"})
#if defined(Custom)
#define SUPPRESS_WARNINGS_CUSTOM_KEY_UNCHECKED @SuppressWarnings("unchecked")
#else
#define SUPPRESS_WARNINGS_CUSTOM_KEY_UNCHECKED
#endif
#else
#define KEY_GENERIC_CLASS KEY_CLASS
#define KEY_GENERIC_TYPE KEY_TYPE
#define KEY_GENERIC
#define KEY_GENERIC_WILDCARD
#define KEY_EXTENDS_GENERIC
#define KEY_SUPER_GENERIC
#define KEY_GENERIC_CAST
#define KEY_GENERIC_ARRAY_CAST
#define KEY_GENERIC_BIG_ARRAY_CAST
#define SUPPRESS_WARNINGS_KEY_UNCHECKED
#define SUPPRESS_WARNINGS_KEY_RAWTYPES
#define SUPPRESS_WARNINGS_KEY_UNCHECKED_RAWTYPES
#define SUPPRESS_WARNINGS_CUSTOM_KEY_UNCHECKED
#endif
#if VALUES_REFERENCE
#define VALUE_GENERIC_CLASS V
#define VALUE_GENERIC_TYPE V
#define VALUE_GENERIC <V>
#define VALUE_EXTENDS_GENERIC <? extends V>
#define VALUE_GENERIC_CAST (V)
#define VALUE_GENERIC_ARRAY_CAST (V[])
#define SUPPRESS_WARNINGS_VALUE_UNCHECKED @SuppressWarnings("unchecked")
#define SUPPRESS_WARNINGS_VALUE_RAWTYPES @SuppressWarnings("rawtypes")
#else
#define VALUE_GENERIC_CLASS VALUE_CLASS
#define VALUE_GENERIC_TYPE VALUE_TYPE
#define VALUE_GENERIC
#define VALUE_EXTENDS_GENERIC
#define VALUE_GENERIC_CAST
#define VALUE_GENERIC_ARRAY_CAST
#define SUPPRESS_WARNINGS_VALUE_UNCHECKED
#define SUPPRESS_WARNINGS_VALUE_RAWTYPES
#endif
#if KEYS_REFERENCE
#if VALUES_REFERENCE
#define KEY_VALUE_GENERIC <K,V>
#define KEY_VALUE_EXTENDS_GENERIC <? extends K, ? extends V>
#else
#define KEY_VALUE_GENERIC <K>
#define KEY_VALUE_EXTENDS_GENERIC <? extends K>
#endif
#else
#if VALUES_REFERENCE
#define KEY_VALUE_GENERIC <V>
#define KEY_VALUE_EXTENDS_GENERIC <? extends V>
#else
#define KEY_VALUE_GENERIC
#define KEY_VALUE_EXTENDS_GENERIC
#endif
#endif
#if KEYS_REFERENCE || VALUES_REFERENCE
#define SUPPRESS_WARNINGS_KEY_VALUE_UNCHECKED @SuppressWarnings("unchecked")
#define SUPPRESS_WARNINGS_KEY_VALUE_RAWTYPES @SuppressWarnings("rawtypes")
#else
#define SUPPRESS_WARNINGS_KEY_VALUE_UNCHECKED
#define SUPPRESS_WARNINGS_KEY_VALUE_RAWTYPES
#endif
/* Value methods */
#define KEY_VALUE longValue
#define VALUE_VALUE ObjectValue
/* Interfaces (keys) */
#define COLLECTION LongCollection

#define SET LongSet

#define HASH LongHash

#define SORTED_SET LongSortedSet

#define STD_SORTED_SET LongSortedSet

#define FUNCTION Long2ObjectFunction
#define MAP Long2ObjectMap
#define SORTED_MAP Long2ObjectSortedMap
#if KEYS_REFERENCE
#define STD_SORTED_MAP SortedMap

#define STRATEGY Strategy

#else
#define STD_SORTED_MAP Long2ObjectSortedMap

#define STRATEGY PACKAGE.LongHash.Strategy

#endif
#define LIST LongList

#define BIG_LIST LongBigList

#define STACK LongStack

#define PRIORITY_QUEUE LongPriorityQueue

#define INDIRECT_PRIORITY_QUEUE LongIndirectPriorityQueue

#define INDIRECT_DOUBLE_PRIORITY_QUEUE LongIndirectDoublePriorityQueue

#define KEY_ITERATOR LongIterator

#define KEY_ITERABLE LongIterable

#define KEY_BIDI_ITERATOR LongBidirectionalIterator

#define KEY_LIST_ITERATOR LongListIterator

#define KEY_BIG_LIST_ITERATOR LongBigListIterator

#define STD_KEY_ITERATOR LongIterator

#define KEY_COMPARATOR LongComparator

/* Interfaces (values) */
#define VALUE_COLLECTION ObjectCollection

#define VALUE_ARRAY_SET ObjectArraySet

#define VALUE_ITERATOR ObjectIterator

#define VALUE_LIST_ITERATOR ObjectListIterator

/* Abstract implementations (keys) */
#define ABSTRACT_COLLECTION AbstractLongCollection

#define ABSTRACT_SET AbstractLongSet

#define ABSTRACT_SORTED_SET AbstractLongSortedSet
#define ABSTRACT_FUNCTION AbstractLong2ObjectFunction
#define ABSTRACT_MAP AbstractLong2ObjectMap
#define ABSTRACT_FUNCTION AbstractLong2ObjectFunction
#define ABSTRACT_SORTED_MAP AbstractLong2ObjectSortedMap
#define ABSTRACT_LIST AbstractLongList

#define ABSTRACT_BIG_LIST AbstractLongBigList

#define SUBLIST LongSubList

#define ABSTRACT_PRIORITY_QUEUE AbstractLongPriorityQueue

#define ABSTRACT_STACK AbstractLongStack

#define KEY_ABSTRACT_ITERATOR AbstractLongIterator

#define KEY_ABSTRACT_BIDI_ITERATOR AbstractLongBidirectionalIterator

#define KEY_ABSTRACT_LIST_ITERATOR AbstractLongListIterator

#define KEY_ABSTRACT_BIG_LIST_ITERATOR AbstractLongBigListIterator

#if KEY_CLASS_Object
#define KEY_ABSTRACT_COMPARATOR Comparator

#else
#define KEY_ABSTRACT_COMPARATOR AbstractLongComparator

#endif
/* Abstract implementations (values) */
#define VALUE_ABSTRACT_COLLECTION AbstractObjectCollection

#define VALUE_ABSTRACT_ITERATOR AbstractObjectIterator

#define VALUE_ABSTRACT_BIDI_ITERATOR AbstractObjectBidirectionalIterator

/* Static containers (keys) */
#define COLLECTIONS LongCollections

#define SETS LongSets

#define SORTED_SETS LongSortedSets

#define LISTS LongLists

#define BIG_LISTS LongBigLists

#define MAPS Long2ObjectMaps
#define FUNCTIONS Long2ObjectFunctions
#define SORTED_MAPS Long2ObjectSortedMaps
#define PRIORITY_QUEUES LongPriorityQueues

#define HEAPS LongHeaps

#define SEMI_INDIRECT_HEAPS LongSemiIndirectHeaps

#define INDIRECT_HEAPS LongIndirectHeaps

#define ARRAYS LongArrays

#define BIG_ARRAYS LongBigArrays

#define ITERATORS LongIterators

#define BIG_LIST_ITERATORS LongBigListIterators

#define COMPARATORS LongComparators

/* Static containers (values) */
#define VALUE_COLLECTIONS ObjectCollections

#define VALUE_SETS ObjectSets

#define VALUE_ARRAYS ObjectArrays

/* Implementations */
#define OPEN_HASH_SET LongOpenHashSet

#define OPEN_HASH_BIG_SET LongOpenHashBigSet

#define OPEN_DOUBLE_HASH_SET LongOpenDoubleHashSet

#define OPEN_HASH_MAP Long2ObjectOpenHashMap

#define OPEN_HASH_BIG_MAP Long2ObjectOpenHashBigMap

#define STRIPED_OPEN_HASH_MAP StripedLong2ObjectOpenHashMap

#define OPEN_DOUBLE_HASH_MAP Long2ObjectOpenDoubleHashMap

#define ARRAY_SET LongArraySet

#define ARRAY_MAP Long2ObjectArrayMap

#define LINKED_OPEN_HASH_SET LongLinkedOpenHashSet

#define AVL_TREE_SET LongAVLTreeSet

#define RB_TREE_SET LongRBTreeSet

#define AVL_TREE_MAP Long2ObjectAVLTreeMap

#define RB_TREE_MAP Long2ObjectRBTreeMap

#define ARRAY_LIST LongArrayList

#define BIG_ARRAY_BIG_LIST LongBigArrayBigList

#define ARRAY_FRONT_CODED_LIST LongArrayFrontCodedList

#define HEAP_PRIORITY_QUEUE LongHeapPriorityQueue

#define HEAP_SEMI_INDIRECT_PRIORITY_QUEUE LongHeapSemiIndirectPriorityQueue

#define HEAP_INDIRECT_PRIORITY_QUEUE LongHeapIndirectPriorityQueue

#define HEAP_SESQUI_INDIRECT_DOUBLE_PRIORITY_QUEUE LongHeapSesquiIndirectDoublePriorityQueue

#define HEAP_INDIRECT_DOUBLE_PRIORITY_QUEUE LongHeapIndirectDoublePriorityQueue

#define ARRAY_FIFO_QUEUE LongArrayFIFOQueue

#define ARRAY_PRIORITY_QUEUE LongArrayPriorityQueue

#define ARRAY_INDIRECT_PRIORITY_QUEUE LongArrayIndirectPriorityQueue

#define ARRAY_INDIRECT_DOUBLE_PRIORITY_QUEUE LongArrayIndirectDoublePriorityQueue

/* Synchronized wrappers */
#define SYNCHRONIZED_COLLECTION SynchronizedLongCollection

#define SYNCHRONIZED_SET SynchronizedLongSet

#define SYNCHRONIZED_SORTED_SET SynchronizedLongSortedSet

#define SYNCHRONIZED_FUNCTION SynchronizedLong2ObjectFunction

#define SYNCHRONIZED_MAP SynchronizedLong2ObjectMap

#define SYNCHRONIZED_LIST SynchronizedLongList

/* Unmodifiable wrappers */
#define UNMODIFIABLE_COLLECTION UnmodifiableLongCollection

#define UNMODIFIABLE_SET UnmodifiableLongSet

#define UNMODIFIABLE_SORTED_SET UnmodifiableLongSortedSet

#define UNMODIFIABLE_FUNCTION UnmodifiableLong2ObjectFunction

#define UNMODIFIABLE_MAP UnmodifiableLong2ObjectMap

#define UNMODIFIABLE_LIST UnmodifiableLongList

#define UNMODIFIABLE_KEY_ITERATOR UnmodifiableLongIterator

#define UNMODIFIABLE_KEY_BIDI_ITERATOR UnmodifiableLongBidirectionalIterator

#define UNMODIFIABLE_KEY_LIST_ITERATOR UnmodifiableLongListIterator

/* Other wrappers */
#define KEY_READER_WRAPPER LongReaderWrapper

#define KEY_DATA_INPUT_WRAPPER LongDataInputWrapper

/* Methods (keys) */
#define NEXT_KEY nextLong
#define PREV_KEY previousLong
#define FIRST_KEY firstLongKey
#define LAST_KEY lastLongKey
#define GET_KEY getLong
#define REMOVE_KEY removeLong
#define READ_KEY readLong
#define WRITE_KEY writeLong
#define DEQUEUE dequeueLong
#define DEQUEUE_LAST dequeueLastLong
#define SUBLIST_METHOD longSubList
#define SINGLETON_METHOD longSingleton

#define FIRST firstLong
#define LAST lastLong
#define TOP topLong
#define PEEK peekLong
#define POP popLong
#define KEY_ITERATOR_METHOD longIterator

#define KEY_LIST_ITERATOR_METHOD longListIterator

#define KEY_EMPTY_ITERATOR_METHOD emptyLongIterator

#define AS_KEY_ITERATOR asLongIterator

#define AS_KEY_ITERABLE asLongIterable

#define TO_KEY_ARRAY toLongArray
#define ENTRY_GET_KEY getLongKey
#define REMOVE_FIRST_KEY removeFirstLong
#define REMOVE_LAST_KEY removeLastLong
#define PARSE_KEY parseLong
#define LOAD_KEYS loadLongs
#define LOAD_KEYS_BIG loadLongsBig
#define STORE_KEYS storeLongs
/* Methods (values) */
#define NEXT_VALUE next
#define PREV_VALUE previous
#define READ_VALUE readObject
#define WRITE_VALUE writeObject
#define VALUE_ITERATOR_METHOD objectIterator

#define ENTRY_GET_VALUE getValue
#define REMOVE_FIRST_VALUE removeFirst
#define REMOVE_LAST_VALUE removeLast
/* Methods (keys/values) */
#define ENTRYSET long2ObjectEntrySet
/* Methods that have special names depending on keys (but the special names depend on values) */
#if KEYS_REFERENCE
#define GET_VALUE get
#define REMOVE_VALUE remove
#else
#define GET_VALUE get
#define REMOVE_VALUE remove
#endif
/* Equality */
#define KEY_EQUALS_NOT_NULL_CAST(x,y) KEY_EQUALS_NOT_NULL(x,y)
#define KEY2INTHASH_CAST(x) KEY2INTHASH(x)

#if KEY_CLASS_Object
#define KEY_EQUALS(x,y) ( (x) == null ? (y) == null : (x).equals(y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( (x).equals(y) )
#define KEY_IS_NULL(x) ( (x) == null )
#elif KEY_CLASS_Float
#define KEY_EQUALS(x,y) ( Float.floatToIntBits(x) == Float.floatToIntBits(y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( Float.floatToIntBits(x) == Float.floatToIntBits(y) )
#define KEY_IS_NULL(x) ( Float.floatToIntBits(x) == 0 )
#elif KEY_CLASS_Double
#define KEY_EQUALS(x,y) ( Double.doubleToLongBits(x) == Double.doubleToLongBits(y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( Double.doubleToLongBits(x) == Double.doubleToLongBits(y) )
#define KEY_IS_NULL(x) ( Double.doubleToLongBits(x) == 0 )
#else
#define KEY_EQUALS(x,y) ( (x) == (y) )
#define KEY_EQUALS_NOT_NULL(x,y) ( (x) == (y) )
#define KEY_IS_NULL(x) ( (x) == KEY_NULL )
#endif

#ifdef Custom
#undef KEY_EQUALS
#define KEY_EQUALS(x,y) ( strategy.equals( (x), (y) ) )
#undef KEY_EQUALS_NOT_NULL
#define KEY_EQUALS_NOT_NULL(x,y) ( strategy.equals( (x), (y) ) )
#undef KEY_EQUALS_NOT_NULL_CAST
#define KEY_EQUALS_NOT_NULL_CAST(x,y) ( strategy.equals(  KEY_GENERIC_CAST (x), (y) ) )
#define KEY_EQUALS_NULL(x) ( strategy.equals( (x), KEY_NULL ) )
#else
#define KEY_EQUALS_NULL(x) KEY_IS_NULL(x)
#endif

#if VALUE_CLASS_Object
#define VALUE_EQUALS(x,y) ( (x) == null ? (y) == null : (x).equals(y) )
#else
#define VALUE_EQUALS(x,y) ( (x) == (y) )
#endif

/* Object/Reference-only definitions (keys) */
#if KEYS_REFERENCE
#define REMOVE remove
#define KEY_OBJ2TYPE(x) (x)
#define KEY_CLASS2TYPE(x) (x)
#define KEY2OBJ(x) (x)
#ifdef Custom
#define KEY2JAVAHASH_NOT_NULL(x) ( strategy.hashCode(x) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( strategy.hashCode(x) ) )
#undef KEY2INTHASH_CAST
#define KEY2INTHASH_CAST(x) ( it.unimi.dsi.fastutil.HashCommon.mix( strategy.hashCode(  KEY_GENERIC_CAST  x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (long)( strategy.hashCode(x)) ) ) )
#elif KEY_CLASS_Object
#define KEY2JAVAHASH_NOT_NULL(x) ( (x).hashCode() )
#define KEY2JAVAHASH(x) ( (x) == null ? 0 : (x).hashCode() )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (x).hashCode() ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (long)( (x).hashCode() ) ) )
#else
#define KEY2JAVAHASH_NOT_NULL(x) ( System.identityHashCode(x) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( System.identityHashCode(x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (long)( System.identityHashCode(x) ) ) )
#endif
#define KEY_CMP(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) )
#define KEY_CMP_EQ(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) == 0 )
#define KEY_LESS(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) < 0 )
#define KEY_LESSEQ(x,y) ( ((Comparable<KEY_GENERIC_CLASS>)(x)).compareTo(y) <= 0 )
#define KEY_NULL (null)
#else
/* Primitive-type-only definitions (keys) */
#define REMOVE rem
#define KEY_CLASS2TYPE(x) ((x).KEY_VALUE())
#define KEY_OBJ2TYPE(x) (KEY_CLASS2TYPE((KEY_CLASS)(x)))
#define KEY2OBJ(x) (KEY_CLASS.valueOf(x))
#if KEY_CLASS_Boolean
#define KEY_CMP_EQ(x,y) ( (x) == (y) )
#define KEY_NULL (false)
#define KEY_CMP(x,y) ( KEY_CLASS.compare((x),(y)) )
#define KEY_LESS(x,y) ( !(x) && (y) )
#define KEY_LESSEQ(x,y) ( !(x) || (y) )
#else
#if KEY_CLASS_Byte || KEY_CLASS_Short || KEY_CLASS_Character
#define KEY_NULL ((KEY_TYPE)0)
#else
#define KEY_NULL (0)
#endif
#if KEY_CLASS_Float || KEY_CLASS_Double
#define KEY_CMP_EQ(x,y) ( KEY_CLASS.compare((x),(y)) == 0 )
#define KEY_CMP(x,y) ( KEY_CLASS.compare((x),(y)) )
#define KEY_LESS(x,y) ( KEY_CLASS.compare((x),(y)) < 0 )
#define KEY_LESSEQ(x,y) ( KEY_CLASS.compare((x),(y)) <= 0 )
#else
#define KEY_CMP_EQ(x,y) ( (x) == (y) )
#define KEY_CMP(x,y) ( KEY_CLASS.compare((x),(y)) )
#define KEY_LESS(x,y) ( (x) < (y) )
#define KEY_LESSEQ(x,y) ( (x) <= (y) )
#endif
#if KEY_CLASS_Float
#define KEY2LEXINT(x) fixFloat(x)
#elif KEY_CLASS_Double
#define KEY2LEXINT(x) fixDouble(x)
#else
#define KEY2LEXINT(x) (x)
#endif
#endif
#ifdef Custom
#define KEY2JAVAHASH_NOT_NULL(x) ( strategy.hashCode(x) )
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( strategy.hashCode(x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (long)( strategy.hashCode(x) ) ) )
#else
#if KEY_CLASS_Float
#define KEY2JAVAHASH_NOT_NULL(x) it.unimi.dsi.fastutil.HashCommon.float2int(x)
#define KEY2INTHASH(x) it.unimi.dsi.fastutil.HashCommon.mix( it.unimi.dsi.fastutil.HashCommon.float2int(x) )
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.mix( (long)( it.unimi.dsi.fastutil.HashCommon.float2int(x) ) )
#define INT(x) (x)
#elif KEY_CLASS_Double
#define KEY2JAVAHASH_NOT_NULL(x) it.unimi.dsi.fastutil.HashCommon.double2int(x)
#define KEY2INTHASH(x) (int)it.unimi.dsi.fastutil.HashCommon.mix( Double.doubleToRawLongBits(x) )
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.mix( Double.doubleToRawLongBits(x) )
#define INT(x) (int)(x)
#elif KEY_CLASS_Long
#define KEY2JAVAHASH_NOT_NULL(x) it.unimi.dsi.fastutil.HashCommon.long2int(x)
#define KEY2INTHASH(x) (int)it.unimi.dsi.fastutil.HashCommon.mix( (x) )
#define KEY2LONGHASH(x) it.unimi.dsi.fastutil.HashCommon.mix( (x) )
#define INT(x) (int)(x)
#elif KEY_CLASS_Boolean
#define KEY2JAVAHASH_NOT_NULL(x) ((x) ? 1231 : 1237)
#define KEY2INTHASH(x) ((x) ? 0xfab5368 : 0xcba05e7b)
#define KEY2LONGHASH(x) ((x) ? 0x74a19fc8b6428188L : 0xbaeca2031a4fd9ecL)
#else
#define KEY2JAVAHASH_NOT_NULL(x) (x)
#define KEY2INTHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (x) ) )
#define KEY2LONGHASH(x) ( it.unimi.dsi.fastutil.HashCommon.mix( (long)( (x) ) ) )
#define INT(x) (x)
#endif
#endif
#endif
#ifndef KEY2JAVAHASH
#define KEY2JAVAHASH(x) KEY2JAVAHASH_NOT_NULL(x)
#endif
/* Object/Reference-only definitions (values) */
#if VALUES_REFERENCE
#define VALUE_OBJ2TYPE(x) (x)
#define VALUE_CLASS2TYPE(x) (x)
#define VALUE2OBJ(x) (x)
#if VALUE_CLASS_Object
#define VALUE2JAVAHASH(x) ( (x) == null ? 0 : (x).hashCode() )
#else
#define VALUE2JAVAHASH(x) ( (x) == null ? 0 : System.identityHashCode(x) )
#endif
#define VALUE_NULL (null)
#define OBJECT_DEFAULT_RETURN_VALUE (this.defRetValue)
#else
/* Primitive-type-only definitions (values) */
#define VALUE_CLASS2TYPE(x) ((x).VALUE_VALUE())
#define VALUE_OBJ2TYPE(x) (VALUE_CLASS2TYPE((VALUE_CLASS)(x)))
#define VALUE2OBJ(x) (VALUE_CLASS.valueOf(x))
#if VALUE_CLASS_Float || VALUE_CLASS_Double || VALUE_CLASS_Long
#define VALUE_NULL (0)
#define VALUE2JAVAHASH(x) it.unimi.dsi.fastutil.HashCommon.Object2int(x)
#elif VALUE_CLASS_Boolean
#define VALUE_NULL (false)
#define VALUE2JAVAHASH(x) (x ? 1231 : 1237)
#else
#if VALUE_CLASS_Integer
#define VALUE_NULL (0)
#else
#define VALUE_NULL ((VALUE_TYPE)0)
#endif
#define VALUE2JAVAHASH(x) (x)
#endif
#define OBJECT_DEFAULT_RETURN_VALUE (null)
#endif
/* START_OF_JAVA_SOURCE */
#include "drv/TextIOFragment.drv"

