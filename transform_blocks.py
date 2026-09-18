import re

path = r"fabric\src\main\java\top\mcmtr\mod\Blocks.java"
with open(path, encoding="utf-8") as f:
    content = f.read()

m = re.search(r"static \{(.*?)\n    \}", content, re.DOTALL)
if not m:
    raise SystemExit("no static block")
body = m.group(1)

lines = []
for line in body.strip().split("\n"):
    line = line.strip().rstrip(";")
    if not line or line.startswith("NativeRegistration"):
        continue

    m1 = re.match(
        r"(\w+) = Init\.REGISTRY\.registerBlockWithBlockItem\(new Identifier\(Init\.MOD_ID, \"([^\"]+)\"\), \(\) -> new Block\(new (\w+)\(\)\), CreativeModeTabs\.(\w+)\)",
        line,
    )
    if m1:
        field, reg_path, cls, tab = m1.groups()
        lines.append(
            f'    public static final ObjectHolder<Block> {field} = MSDRegistry.registerBlockWithBlockItem("{reg_path}", {cls}::new, true, CreativeModeTabs.{tab});'
        )
        continue

    m2 = re.match(
        r"(\w+) = Init\.REGISTRY\.registerBlock\(new Identifier\(Init\.MOD_ID, \"([^\"]+)\"\), \(\) -> new Block\(new (\w+)\(\)\)\)",
        line,
    )
    if m2:
        field, reg_path, cls = m2.groups()
        lines.append(
            f'    public static final ObjectHolder<Block> {field} = MSDRegistry.registerBlock("{reg_path}", {cls}::new);'
        )
        continue

    m3 = re.match(
        r"(\w+) = Init\.REGISTRY\.registerBlockWithBlockItem\(new Identifier\(Init\.MOD_ID, \"([^\"]+)\"\), \(\) -> new Block\(new (\w+)\(([^)]*)\)\), CreativeModeTabs\.(\w+)\)",
        line,
    )
    if m3:
        field, reg_path, cls, args, tab = m3.groups()
        lines.append(
            f'    public static final ObjectHolder<Block> {field} = MSDRegistry.registerBlockWithBlockItem("{reg_path}", settings -> new {cls}({args}), true, CreativeModeTabs.{tab});'
        )
        continue

    print("UNMATCHED:", line[:150])

header = """package top.mcmtr.mod;

import net.minecraft.world.level.block.Block;
import org.mtr.registry.ObjectHolder;
import top.mcmtr.mod.blocks.*;
import top.mcmtr.mod.blocks.old.*;
import top.mcmtr.mod.registry.MSDRegistry;
import top.mcmtr.mod.registry.NativeRegistration;

public final class Blocks {

    static {
        NativeRegistration.init();
    }

"""

footer = """
    public static void init() {
        Init.MSD_LOGGER.info("Registering MTR Station Decoration blocks");
    }
}
"""

new_content = header + "\n".join(lines) + footer
with open(path, "w", encoding="utf-8", newline="\n") as f:
    f.write(new_content)
print("Wrote", len(lines), "registrations")
