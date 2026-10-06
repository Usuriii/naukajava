package pd4.model.enums;

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
