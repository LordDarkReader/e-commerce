export interface ProductDescRequest {
  id?: number;
  name?: string;
  description?: string;
  price?: number;
  categoryId?: number;
  categoryName?: string;
  categoryDescription?: string;
  filename?: string;
  file?: FormData;
}
