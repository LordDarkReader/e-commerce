export interface AddProductRequest {
  id?: number;
  name?: string;
  description?: string;
  //availableQuantity?: number;
  price?: number;
  categoryId?: number;
  categoryName?: string;
  categoryDescription?: string;
  image?: FormData;
  //image?: File;
}
