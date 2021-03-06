/**
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2008 Karthik Kumar.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0 
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package org.jreversepro.ast.evaluator;


import java.util.Arrays;

import org.jreversepro.ast.expression.Variable;
import org.jreversepro.reflect.instruction.Instruction;


public class DLoadEvaluator extends AbstractInstructionEvaluator {

  public DLoadEvaluator(EvaluatorContext context) {
    super(context);
  }

  @Override
  void evaluate(Instruction ins) {
    switch (ins.opcode) {
    case OPCODE_DLOAD:
      operateLoadInstruction(ins, ins.getArgUnsignedWide());
      break;
    case OPCODE_DLOAD_0:
    case OPCODE_DLOAD_1:
    case OPCODE_DLOAD_2:
    case OPCODE_DLOAD_3:
      operateLoadInstruction(ins, ins.opcode - OPCODE_DLOAD_0);
      break;

    }
  }

  private void operateLoadInstruction(Instruction ins,
      int variableIndexToSymbolTable) {
    Variable var = new Variable(varTable, JVM_TYPE_DOUBLE,
        variableIndexToSymbolTable, ins.currentPc);
    evalMachine.push(var);
  }

  @Override
  Iterable<Integer> getProcessingOpcodes() {
    return Arrays.asList(OPCODE_DLOAD, OPCODE_DLOAD_0, OPCODE_DLOAD_1,
        OPCODE_DLOAD_2, OPCODE_DLOAD_3);
  }
}
