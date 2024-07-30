/*
 * Copyright 2021 Marc Liberatore.
 */

 package sequencer;

 import java.util.ArrayList;
 import java.util.List;
 
 public class Assembler {
 
	 private List<Fragment> fragments;
 
	 public Assembler(List<Fragment> fragments) {
		 this.fragments = new ArrayList<>(fragments);
	 }
 
	 public List<Fragment> getFragments() {
		 return fragments;
	 }
 
	 public boolean assembleOnce() {
		 int maxOverlap = -1;
		 Fragment mergedFragment = null;
		 int mergeIndex1 = -1;
		 int mergeIndex2 = -1;
 
		 for (int i = 0; i < fragments.size(); i++) {
			 for (int j = 0; j < fragments.size(); j++) {
				 if (i != j) {
					 int overlap = fragments.get(i).calculateOverlap(fragments.get(j));
					 if (overlap > maxOverlap) {
						 maxOverlap = overlap;
						 mergedFragment = fragments.get(i).mergedWith(fragments.get(j));
						 mergeIndex1 = i;
						 mergeIndex2 = j;
					 }
				 }
			 }
		 }
 
		 if (maxOverlap >= 1) {
			 fragments.remove(mergeIndex1);
			 if (mergeIndex1 < mergeIndex2) {
				 fragments.remove(mergeIndex2 - 1);
			 } else {
				 fragments.remove(mergeIndex2);
			 }
			 fragments.add(mergedFragment);
			 return true;
		 }
 
		 return false;
	 }
 
	 public void assembleAll() {
		 while (assembleOnce()) {
			 // Continue assembling until no more assemblies can be performed
		 }
	 }
 }
 