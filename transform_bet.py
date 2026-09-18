import re

path = r"fabric\src\main\java\top\mcmtr\mod\BlockEntityTypes.java"
with open(path, encoding="utf-8") as f:
    content = f.read()

m = re.search(r"static \{(.*?)\n    \}", content, re.DOTALL)
body = m.group(1)

lines = []
for line in body.strip().split("\n"):
    line = line.strip().rstrip(";")
    if not line:
        continue

    m1 = re.match(
        r"(\w+) = Init\.REGISTRY\.registerBlockEntityType\(new Identifier\(Init\.MOD_ID, \"([^\"]+)\"\), (\w+)::new, Blocks\.(\w+)::get\)",
        line,
    )
    if m1:
        field, reg_path, factory, block = m1.groups()
        lines.append(
            f'        {field} = RegistryServer.registerBlockEntityType("{reg_path}", {factory}::new, Blocks.{block}::get);'
        )
        continue

    m2 = re.match(
        r"(\w+) = Init\.REGISTRY\.registerBlockEntityType\(new Identifier\(Init\.MOD_ID, \"([^\"]+)\"\), \(pos, state\) -> new (\w+)\.(\w+)\(([^)]*)\), Blocks\.(\w+)::get\)",
        line,
    )
    if m2:
        field, reg_path, cls, inner, args, block = m2.groups()
        lines.append(
            f'        {field} = RegistryServer.registerBlockEntityType("{reg_path}", (pos, state) -> new {cls}.{inner}({args}), Blocks.{block}::get);'
        )
        continue

    print("UNMATCHED:", line[:150])

# collect field declarations from original
fields = re.findall(r"public static final BlockEntityTypeRegistryObject<([^>]+)> (\w+);", content)

header = """package top.mcmtr.mod;

import net.minecraft.world.level.block.entity.BlockEntityType;
import org.mtr.registry.ObjectHolder;
import org.mtr.registry.RegistryServer;
import top.mcmtr.mod.blocks.*;
import top.mcmtr.mod.blocks.old.*;

public final class BlockEntityTypes {

    static {
"""
footer = """
    }

"""
decls = []
for typ, name in fields:
    decls.append(f"    public static ObjectHolder<BlockEntityType<{typ}>> {name};")

footer += "\n".join(decls) + """

    public static void init() {
        Init.MSD_LOGGER.info("Registering MTR Station Decoration block entity types");
    }
}
"""

with open(path, "w", encoding="utf-8", newline="\n") as f:
    f.write(header + "\n".join(lines) + footer)
print("Wrote", len(lines), "bet registrations")
