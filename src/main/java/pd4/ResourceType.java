package pd4;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResourceType {
    POWER_TOOLS("Elektronarzędzia"),
    HEAVY_MACHINERY("Ciężki sprzęt");

    final String description;

    @Override
    public String toString() {
        return description;
    }
}
