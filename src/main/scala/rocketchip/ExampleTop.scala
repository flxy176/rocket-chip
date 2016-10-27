// See LICENSE for license details.

package rocketchip

import Chisel._
import cde.{Parameters, Field}
import junctions._
import coreplex._
import rocketchip._

/** Example Top with Periphery */
class ExampleTop[+C <: BaseCoreplex](buildCoreplex: Parameters => C)(implicit p: Parameters) extends BaseTop(buildCoreplex)
    with PeripheryBootROM
    with PeripheryDebug
    with PeripheryExtInterrupts
    with PeripheryMasterMem
    with PeripheryMasterAXI4MMIO
    with PeripherySlave
    with DirectConnection {
  override lazy val module = new ExampleTopModule(this, new ExampleTopBundle(this))
}

class ExampleTopBundle[+L <: ExampleTop[BaseCoreplex]](outer: L) extends BaseTopBundle(outer)
    with PeripheryBootROMBundle
    with PeripheryDebugBundle
    with PeripheryExtInterruptsBundle
    with PeripheryMasterMemBundle
    with PeripheryMasterAXI4MMIOBundle
    with PeripherySlaveBundle

class ExampleTopModule[+L <: ExampleTop[BaseCoreplex], +B <: ExampleTopBundle[L]](outer: L, io: B) extends BaseTopModule(outer, io)
    with PeripheryBootROMModule
    with PeripheryDebugModule
    with PeripheryExtInterruptsModule
    with PeripheryMasterMemModule
    with PeripheryMasterAXI4MMIOModule
    with PeripherySlaveModule
    with HardwiredResetVector
    with DirectConnectionModule

/** Example Top with TestRAM */
class ExampleTopWithTestRAM[+C <: BaseCoreplex](buildCoreplex: Parameters => C)(implicit p: Parameters) extends ExampleTop(buildCoreplex)
    with PeripheryTestRAM {
  override lazy val module = new ExampleTopWithTestRAMModule(this, new ExampleTopWithTestRAMBundle(this))
}

class ExampleTopWithTestRAMBundle[+L <: ExampleTopWithTestRAM[BaseCoreplex]](outer: L) extends ExampleTopBundle(outer)
    with PeripheryTestRAMBundle

class ExampleTopWithTestRAMModule[+L <: ExampleTopWithTestRAM[BaseCoreplex], +B <: ExampleTopWithTestRAMBundle[L]](outer: L, io: B) extends ExampleTopModule(outer, io)
    with PeripheryTestRAMModule
