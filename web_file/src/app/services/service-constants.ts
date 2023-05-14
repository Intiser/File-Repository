export class ServiceConstants{
    public static readonly AUTHORIZATION = "AUTHORIZATION";
    public static readonly ADMIN = "ADMIN";

    static values(): string[] {
        return Object.values(ServiceConstants);
    }
}